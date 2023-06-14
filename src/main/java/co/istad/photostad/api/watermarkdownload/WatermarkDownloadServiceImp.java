package co.istad.photostad.api.watermarkdownload;

import co.istad.photostad.api.watermark.web.CreateWatermarkDto;
import co.istad.photostad.api.watermarkdownload.web.CreateWatermarkDownloadDto;
import co.istad.photostad.api.watermarkdownload.web.WatermarkDownloadDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class WatermarkDownloadServiceImp implements WatermarkDownloadService {
    private final WatermarkDownloadMapper watermarkDownloadMapper;
    private final WatermarkDownloadMapStruct watermarkDownloadMapStruct;

    @Override
    public WatermarkDownloadDto insertWatermarkDownload(CreateWatermarkDownloadDto createWatermarkDownloadDto) {
        WatermarkDownload watermarkDownload = watermarkDownloadMapStruct.createWatermarkDownloadDtoToWatermarkDownload(createWatermarkDownloadDto);
        watermarkDownload.setDownloadAt(new Timestamp(System.currentTimeMillis()));
        watermarkDownload.setUuid(UUID.randomUUID().toString());
        if (watermarkDownloadMapper.insert(watermarkDownload)) {
            return this.findWatermarkDownloadById(watermarkDownload.getId());
        }
        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "watermark download insert is fail. Try again"
        );
    }

    @Override
    public WatermarkDownloadDto findWatermarkDownloadById(Integer id) {
        WatermarkDownload watermarkDownload = watermarkDownloadMapper.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Watermark download with id %d is not found", id)
                )
        );
        return watermarkDownloadMapStruct.watermarkDownloadToWatermarkDownloadDto(watermarkDownload);
    }

    @Override
    public PageInfo<WatermarkDownloadDto> findWatermarkDownloadAll(int page, int limit) {
        PageInfo<WatermarkDownload> watermarkDownloadPageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(
                () -> watermarkDownloadMapper.findAll()
        );
        return watermarkDownloadMapStruct.pageInnfoWatermarkToPageInfoWatermarkPageInfo(watermarkDownloadPageInfo);
    }

    @Override
    public Integer deleteWatermarkDownloadById(Integer id) {
        if (watermarkDownloadMapper.isExistById(id)) {
            if (watermarkDownloadMapper.deleteById(id)) {
                return id;
            }
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "delete watermark download is fail, Try again"
            );
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Watermark download with id %d is not found", id)
        );
    }

    @Override
    public WatermarkDownloadDto updateWatermarkDownloadById(Integer id, CreateWatermarkDownloadDto createWatermarkDownloadDto) {
        if (watermarkDownloadMapper.isExistById(id)) {
            WatermarkDownload watermarkDownload = watermarkDownloadMapStruct.createWatermarkDownloadDtoToWatermarkDownload(createWatermarkDownloadDto);
            watermarkDownload.setId(id);
            if (watermarkDownloadMapper.updateById(watermarkDownload)) {
                return this.findWatermarkDownloadById(id);
            }
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "update watermark download is fail"
            );
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Watermark download with id %d is not found", id)
        );
    }
}
