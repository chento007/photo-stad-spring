package co.istad.photostad.api.watermark;

import co.istad.photostad.api.json.Layer;
import co.istad.photostad.api.json.Scene;
import co.istad.photostad.api.watermark.web.CreateWatermarkDto;
import co.istad.photostad.api.watermark.web.DailyEditCount;
import co.istad.photostad.api.watermark.web.WatermarkDto;
import co.istad.photostad.api.file.web.FileBase64Dto;
import co.istad.photostad.util.CompressUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.UUID;
import java.util.concurrent.*;

@RequiredArgsConstructor
@Service
public class WatermarkServiceImp implements WatermarkService {
    private final WatermarkMapper watermarkMapper;
    private final WatermarkMapStruct watermarkMapStruct;
    private final CompressUtil compressUtil;
    @Value("${file.server-path}")
    private String fileSever;
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Override
    public Resource downloadByZip(CreateWatermarkDto createWatermarkDto) {
        // TODO step 1 : create frame for background
        BufferedImage frameBackground = compressUtil.createFrame(createWatermarkDto.editorJson());
        // TODO step 2 : upload image base 64 into server
        for (Scene scene : createWatermarkDto.editorJson().getScenes()) {
            for (Layer layer : scene.getLayers()) {
                if (layer.getPreview() != null) {
                    FileBase64Dto fileBase64Dto = compressUtil.uploadFileBase64(layer);
                    if (fileBase64Dto.status()) {
                        layer.setPreview(fileBase64Dto.image());
                    }
                }
            }
        }
        // TODO step 2 : compress image
        for (Scene scene : createWatermarkDto.editorJson().getScenes()) {
            BufferedImage imageCompress = frameBackground;
            for (Layer layer : scene.getLayers()) {
                if (layer.getPreview() != null) {
                    imageCompress = compressUtil.compressWatermark(imageCompress, layer);
                } else if (layer.getText() != null) {
                    if (layer.getType().equalsIgnoreCase("StaticText")) {
                        imageCompress = compressUtil.compressText(imageCompress, layer);
                    }
                }
            }
            // TODO step 3 : upload image after into sever
            try {
                ImageIO.write(imageCompress, "jpg", new File(fileSever + UUID.randomUUID().toString()));
            } catch (IOException e) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "upload image after compress failed. Please try again."
                );
            }
        }
        return null;
    }

    @Override
    public DailyEditCount dailyEdit() {
        return new DailyEditCount(watermarkMapper.dailyEditCount());
    }

    @Override
    public WatermarkDto insertWatermark(CreateWatermarkDto createWatermarkDto) {
        Watermark watermark = watermarkMapStruct.createWatermarkDtoToWatermark(createWatermarkDto);
        watermark.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        watermark.setUuid(UUID.randomUUID().toString());
        if (watermarkMapper.insert(watermark)) {
            return this.selectWatermarkById(watermark.getId());
        }
        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "watermark insert has been fail"
        );
    }

    @Override
    public WatermarkDto selectWatermarkById(Integer id) {
        Watermark watermark = watermarkMapper.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("watermark with %d is not found", id)
                )
        );
        return watermarkMapStruct.watermarkToWatermarkDto(watermark);
    }

    @Override
    public Integer deleteWatermarkById(Integer id) {
        if (watermarkMapper.isWatermarkIdExist(id)) {
            if (watermarkMapper.deleteById(id)) {
                return id;
            }
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("delete watermark with %d is fail", id)
            );
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("watermark with %d is not found", id)
        );
    }

    @Override
    public PageInfo<WatermarkDto> selectAllWatermark(int page, int limit) {
        PageInfo<Watermark> watermarkPageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(
                () -> watermarkMapper.findAll()
        );
        return watermarkMapStruct.watermarkPageinfoToWatermarkDtoPageInfo(watermarkPageInfo);
    }

    @Override
    public WatermarkDto updateWatermark(Integer id, CreateWatermarkDto createWatermarkDto) {
        if (watermarkMapper.isWatermarkIdExist(id)) {
            Watermark watermark = watermarkMapStruct.createWatermarkDtoToWatermark(createWatermarkDto);
            watermark.setId(id);
            if (watermarkMapper.update(watermark)) {
                return this.selectWatermarkById(id);
            }
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("update watermark with id %d is fail", id)
            );
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("watermark with %d is not found", id)
        );
    }


}
