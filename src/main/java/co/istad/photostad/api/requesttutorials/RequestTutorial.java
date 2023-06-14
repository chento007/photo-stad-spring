package co.istad.photostad.api.requesttutorials;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestTutorial {
    private Integer id;
    private String uuid;
    private Integer userId;
    private String description;
    private Boolean isRead;
}
