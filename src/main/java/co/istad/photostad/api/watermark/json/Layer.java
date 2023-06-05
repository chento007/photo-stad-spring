package co.istad.photostad.api.watermark.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int left;
    private int top;
    private int width;
    private int height;
    private float opacity;
    private String originX;
    private String originY;
    private double scaleX;
    private double scaleY;
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