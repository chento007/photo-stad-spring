package co.istad.photostad.api.watermark;

import co.istad.photostad.api.json.Design;
import co.istad.photostad.api.json.Layer;
import co.istad.photostad.api.json.Scene;
import co.istad.photostad.file.web.FileBase64Dto;
import co.istad.photostad.util.CompressUtil;
import co.istad.photostad.util.FileUtil;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WatermarkServiceImp implements WatermarkService {
    private final FileUtil fileUtil;
    private final CompressUtil compressUtil;

    @Async
    @Override
    public void compress(Design design) {
        // upload image base 64 to sever
        for (Scene scene : design.getScenes()) {
            for (Layer layer : scene.getLayers()) {
                if (layer.getSrc() != null) {
                    FileBase64Dto upload = fileUtil.uploadFileBase64(layer);
                    if (upload.status()) {
                        layer.setSrc(upload.image());
                    }
                }
            }
        }
        // create frame for background
        BufferedImage frameBackground = compressUtil.createFrame(design);
        // get logo
        List<Layer> logoList = new ArrayList<>();
        // compress watermark
        for (Scene scene : design.getScenes()) {
            BufferedImage watermark = frameBackground;
            for (Layer layer : scene.getLayers()) {
                if (layer.getType().equalsIgnoreCase("StaticText") || layer.getName().equalsIgnoreCase("StaticText")) {
                    System.out.println("caption : " + layer.getText());
                    System.out.println("charSpacing: " + layer.getCharSpacing());
                    System.out.println("fontFamily: " + layer.getFontFamily());
                    System.out.println("fontSize: " + layer.getFontSize());
                    System.out.println("textAlign: " + layer.getTextAlign());
                    System.out.println("fontURL: " + layer.getFontURL());
                } else if (layer.getSrc() != null) {
                    watermark = compressUtil.compressWatermark(watermark, layer);
                }
            }
            // write image after compress watermark
            compressUtil.writeImageWatermark(watermark);
        }
    }
}
