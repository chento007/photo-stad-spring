package co.istad.photostad.api.auth;

import co.istad.photostad.api.user.Authority;
import co.istad.photostad.api.user.Role;
import co.istad.photostad.api.user.User;
import co.istad.photostad.api.user.UserProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface AuthMapper {
    @InsertProvider(type = AuthProvider.class, method = "buildRegisterSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    boolean register(@Param("u") User user);

    @InsertProvider(type = AuthProvider.class, method = "buildCreateUserRoleSql")
    boolean createUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    @Results(
            id = "authResultMap",
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
                    @Result(column = "id", property = "roles", many = @Many(select = "loadUserRoles"))
            }
    )
    @SelectProvider(type = AuthProvider.class, method = "buildFindUserEmailSql")
    Optional<User> findEmail(@Param("email") String email);

    @SelectProvider(type = AuthProvider.class, method = "buildLoadUserRoleSql")
    @Result(column = "id", property = "authorities", many = @Many(select = "loadUserAuthorities"))
    List<Role> loadUserRoles(@Param("id") Integer id);

    @ResultMap("authResultMap")
    @Select("SELECT *FROM users WHERE email=#{email} AND is_deleted=FALSE AND is_verified=TRUE")
    Optional<User> loadUserByUsername(@Param("email") String email);


    @SelectProvider(type = AuthProvider.class, method = "buildLoadUserAuthoritiesSql")
    List<Authority> loadUserAuthorities(@Param("roleId") Integer roleId);

    @UpdateProvider(type = AuthProvider.class, method = "buildUpdateVerifiedCodeSql")
    boolean updateVerifiedCode(@Param("email") String email, @Param("verifiedCode") String verifiedCode);

    @SelectProvider(type = AuthProvider.class, method = "buildSelectByEmailAndVerifiedCodeSql")
    @ResultMap("authResultMap")
    Optional<User> selectByEmailAndVerifiedCode(@Param("email") String email, @Param("verifiedCode") String verifiedCode);

    @UpdateProvider(type = AuthProvider.class, method = "buildVerifySql")
    @ResultMap("authResultMap")
    void verify(@Param("email") String email, @Param("verifiedCode") String verifiedCode);

    @UpdateProvider(type = AuthProvider.class, method = "buildUpdateLoginAtSql")
    void updateLoginAt(@Param("u") User user);

    @Select("SELECT *FROM users WHERE email=#{email} AND is_deleted=FALSE")
    Optional<User> selectUserByEmailToUpdateLoginAt(String email);
}
