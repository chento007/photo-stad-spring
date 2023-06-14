package co.istad.photostad.api.role;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class RoleProvider {
    private final String tblName = "roles";

    public String buildSelectAllRoleSql(@Param("name") String name) {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            if (!name.isEmpty()) {
                WHERE("name ILIKE CONCAT('%',#{name},'%')");
            }
        }}.toString();
    }

    public String buildSelectRoleByIdSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            WHERE("id=#{id}");
        }}.toString();
    }

}
