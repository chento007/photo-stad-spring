package co.istad.photostad.api.json;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Layer {
    private String id;
    private String name;
    private double angle;
    private String stroke; // color border
    private int strokeWidth;
    private int left; // coordinate x
    private int top; // coordinate y
    private int width; // thumbnailator
    private int height; // thumbnailator
    private float opacity; // thumbnailator
    private String originX; //thumbnailator
    private String originY; //thumbnailator
    private double scaleX; // use to calculate width
    private double scaleY; // use to calculate height
    private String type;
    private boolean flipX;
    private boolean flipY;
    private double skewX;
    private double skewY;
    private boolean visible;
    private Shadow shadow;
    private Integer charSpacing;
    private String fontFamily;
    private Float fontSize;
    private String text;
    private String textAlign;
    private String fontURL;
    private String src;
    private double cropX;
    private double cropY;
    private String fill;
    private String preview;
    private Object metadata;
}