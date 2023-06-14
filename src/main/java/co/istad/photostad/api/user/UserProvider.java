package co.istad.photostad.api.user;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    private final String tblName = "users";

    public String buildFindUserEmailSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            WHERE("email=#{email}", "is_deleted=FALSE");
        }}.toString();
    }

    public String buildFindAllUserWithNameSql(@Param("name") String name) {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            if (!name.isEmpty()) {
                WHERE("name ILIKE CONCAT('%',#{name},'%')", "is_deleted=false");
            } else {
                WHERE("is_deleted=false");
            }
        }}.toString();
    }

    public String buildFindUserByIdSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            WHERE("id=#{id}", "is_deleted=FALSE");
        }}.toString();
    }

    public String buildDeleteByUpdateIsDeleteByIdSql() {
        return new SQL() {{
            UPDATE(tblName);
            SET("is_deleted=TRUE");
            WHERE("id=#{id}", "is_deleted=FALSE");
        }}.toString();
    }

    public String buildDeleteByIdSql() {
        return new SQL() {{
            DELETE_FROM(tblName);
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildUpdateUserByIdSql() {
        return new SQL() {{
            UPDATE(tblName);
            SET("username=#{u.username}");
            SET("family_name=#{u.familyName}");
            SET("given_name=#{u.givenName}");
            SET("gender=#{u.gender}");
            SET("dob=#{u.dob}");
            SET("avatar=#{u.avatar.id}");
            SET("phone_number=#{u.phoneNumber}");
            SET("address=#{u.address}");
            SET("biography=#{u.biography}");
            WHERE("id=#{u.id}");
        }}.toString();
    }
}
