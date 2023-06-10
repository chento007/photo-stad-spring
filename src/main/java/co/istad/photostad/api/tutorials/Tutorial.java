package co.istad.photostad.api.tutorials;

import co.istad.photostad.api.image.Image;
import co.istad.photostad.api.user.User;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tutorial {
    private Integer id;
    private String uuid;
    private String name;
    private String slug;
    private String title;
    private String description;
    private String htmlContent;
    private Image thumbnail;
    private Integer createdBy;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private Integer viewCount;
    private Boolean isDeleted;
}
