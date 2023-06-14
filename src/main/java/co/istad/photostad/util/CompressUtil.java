package co.istad.photostad.util;

import co.istad.photostad.api.json.Design;
import co.istad.photostad.api.json.Layer;
import co.istad.photostad.api.file.web.FileBase64Dto;
import jakarta.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.filters.Caption;
import net.coobird.thumbnailator.geometry.Coordinate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Base64;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CompressUtil {
    private final FileUtil fileUtil;

    public BufferedImage createFrame(Design design) {
        int width = design.getFrame().getWidth();
        int height = design.getFrame().getHeight();
        Color color = Color.decode(design.getScenes().get(0).getLayers().get(0).getFill());
        BufferedImage frame = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = frame.createGraphics();
        graphics2D.setColor(color);
        graphics2D.fillRect(0, 0, width, height);
        graphics2D.dispose();
        return frame;
    }

    public FileBase64Dto uploadFileBase64(Layer layer) {
        String[] record = layer.getSrc().split(",");
        String extension = fileUtil.getExtensionBase64(record[0]);
        String fileImage = record[1];
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(fileImage);
        String fileName = String.format("%s.%s", UUID.randomUUID(), extension);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
        try {
            BufferedImage originalImage = ImageIO.read(inputStream);
            double widthDouble = layer.getWidth() * layer.getScaleX();
            double heightDouble = layer.getHeight() * layer.getScaleY();
            int width = (int) Math.round(widthDouble);
            int height = (int) Math.round(heightDouble);
            BufferedImage imageCompress = Thumbnails.of(originalImage)
                    .size(width, height)
                    .asBufferedImage();
            ImageIO.write(imageCompress, extension, new File(fileUtil.getFileServerPath() + fileName));
            return new FileBase64Dto(true, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BufferedImage compressText(BufferedImage watermark, Layer layer) {
        Font font = this.loadFont(layer.getFontURL(), layer.getFontSize());
        try {
            if (layer.getShadow() != null) {
                Caption caption= new Caption(layer.getText(),font,this.getColor(layer.getFill()),1.0f,
                        new Coordinate(layer.getLeft(), layer.getTop()),0);
                return Thumbnails.of(watermark)
                        .size(watermark.getWidth(), watermark.getHeight())
                        .addFilter(caption)
                        .asBufferedImage();
            }
            return Thumbnails.of(watermark)
                    .size(watermark.getWidth(), watermark.getHeight())
                    .addFilter(
                            new Caption(
                                    layer.getText(),
                                    font,
                                    this.getColor(layer.getFill()),
                                    1.0f,
                                    new Coordinate(layer.getLeft(), layer.getTop()),
                                    0
                            )
                    )
                    .asBufferedImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage compressWatermark(BufferedImage watermark, Layer layer) {
        try {
            return Thumbnails.of(watermark)
                    .size(watermark.getWidth(), watermark.getHeight())
                    .watermark(
                            new Coordinate(layer.getLeft(), layer.getTop()),
                            ImageIO.read(fileUtil.findByName(layer.getPreview()).getFile()),
                            layer.getOpacity()
                    )
                    .asBufferedImage();
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "design watermark has been fail !!"
            );
        }
    }

    public void writeImageWatermark(BufferedImage image) {
        try {
            ImageIO.write(image, "jpg", new File(fileUtil.getFileServerPath() + UUID.randomUUID() + ".jpg"));
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "uploading watermark has been fail !!"
            );
        }
    }


    public Font loadFont(String fontUrl, Integer size) {
        try {
            URL url = new URL(fontUrl);
            InputStream inputStream = url.openStream();
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(Font.PLAIN, size);
            inputStream.close();
            return baseFont;
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    public BufferedImage base64ToBufferedImage(String base64Image) throws IOException {
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
        BufferedImage bufferedImage = ImageIO.read(bis);
        bis.close();
        return bufferedImage;
    }

    public Color getColor(String fill) {
        return Color.decode(fill);
    }
}
