package co.istad.photostad.api.json;

import lombok.*;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Design implements Serializable {
    private String id;
    private String type;
    private String name;
    private Frame frame;
    private List<Scene> scenes;
    private Object metadata;
    private String preview;
}