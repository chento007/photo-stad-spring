package co.istad.photostad.util;

import co.istad.photostad.api.json.Design;
import co.istad.photostad.api.json.Layer;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Coordinate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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

    public BufferedImage compressWatermark(BufferedImage watermark, Layer layer) {
        try {
            return Thumbnails.of(watermark)
                    .size(watermark.getWidth(), watermark.getHeight())
                    .watermark(
                            new Coordinate(layer.getLeft(), layer.getTop()),
                            ImageIO.read(fileUtil.findByName(layer.getSrc()).getFile()),
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

    public Font getFontFamilyByUrl(String url) {
        try {
            URL fontUrl = new URL(url);
            InputStream fontStream = new BufferedInputStream(fontUrl.openStream());
            return Font.createFont(Font.TRUETYPE_FONT, fontStream);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Color getColor(String fill){
        return Color.decode(fill);
    }
}
