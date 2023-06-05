package co.istad.photostad.api.font;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Font {
    private String id;
    private String family;
    private String fullName;
    private String postScriptName;
    private String preview;
    private String style;
    private String url;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer userId;
}
