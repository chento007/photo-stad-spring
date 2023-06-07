package co.istad.photostad.api.json;

import lombok.*;

import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Scene {
    private String id;
    private List<Layer> layers;
    private String name;
}
