package co.istad.photostad.api.watermark.json;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Design {
    private String id;
    private String type;
    private String name;
    private Frame frame;
    private List<Scene> scenes;
    private Object metadata;
    private String preview;
}