package co.istad.photostad.api.user;

import co.istad.photostad.api.image.Image;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String uuid;
    private String name;
    private String username;
    private String familyName;
    private String givenName;
    private String gender;
    private LocalDate dob;
    private Image avatar;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String biography;
    private Timestamp createdAt;
    private Timestamp loggedInAt;
    private Boolean isVerified;
    private String verifiedCode;
    private Boolean isDeleted;
    private List<Role> roles;
}
