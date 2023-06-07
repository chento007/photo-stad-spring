package co.istad.photostad.api.json;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Shadow {
    private String color;
    private Integer blur;
    private Integer offSetX;
    private Integer offSetY;
    private Boolean affectStroke;
    private Boolean nonScaling ;
}
