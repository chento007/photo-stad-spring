package co.istad.photostad.api.user;

import co.istad.photostad.api.image.Image;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface UserMapper {
    @Select("SELECT EXISTS (SELECT * FROM users WHERE email=#{email}) ")
    Boolean exitsByEmail(@Param("email") String email);

    @Select("SELECT EXISTS (SELECT * FROM roles WHERE id=#{id})")
    Boolean exitByRoleId(@Param("id") Integer id);

    @Results(
            id = "userResultMap",
            value = {
                    @Result(column = "id", property = "id"),
                    @Result(column = "is_verified", property = "isVerified"),
                    @Result(column = "verified_code", property = "verifiedCode"),
                    @Result(column = "family_name", property = "familyName"),
                    @Result(column = "given_name", property = "givenName"),
                    @Result(column = "phone_number", property = "phoneNumber"),
                    @Result(column = "created_at", property = "createdAt"),
                    @Result(column = "logged_in_at", property = "loggedInAt"),
                    @Result(column = "is_deleted", property = "isDeleted"),
                    @Result(column = "avatar", property = "avatar", one = @One(select = "findImageById"))
            }
    )
    @SelectProvider(type = UserProvider.class, method = "buildFindUserEmailSql")
    Optional<User> findEmail(String email);

    @Select("SELECT *FROM images WHERE id=#{id}")
    Image findImageById(@Param("id") Integer id);

    @ResultMap("userResultMap")
    @SelectProvider(type = UserProvider.class, method = "buildFindAllUserWithNameSql")
    List<User> findAll(String name);

    @ResultMap("userResultMap")
    @SelectProvider(type = UserProvider.class, method = "buildFindUserByIdSql")
    Optional<User> findById(@Param("id") Integer id);

    @UpdateProvider(type = UserProvider.class, method = "buildDeleteByUpdateIsDeleteByIdSql")
    boolean deleteByUpdateIsDeletedById(Integer id);

    @DeleteProvider(type = UserProvider.class, method = "buildDeleteByIdSql")
    boolean deleteById(@Param("id") Integer id);

    @UpdateProvider(type = UserProvider.class, method = "buildUpdateUserByIdSql")
    boolean update(@Param("u") User user);

}
