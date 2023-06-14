package co.istad.photostad.api.requesttutorials;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class RequestTutorialProvider {
    private final String tblName = "request_tutorials";

    public String buildFindRequestTutorialByIdSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildInsertRequestTutorialSql() {
        return new SQL() {{
            INSERT_INTO(tblName);
            VALUES("uuid", "#{r.uuid}");
            VALUES("user_id", "#{r.userId}");
            VALUES("description", "#{r.description}");
            VALUES("is_read", "FALSE");

        }}.toString();
    }

    public String buildSelectAllRequestTutorialSql(@Param("name") String name) {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
        }}.toString();
    }

    public String buildUpdateRequestTutorialByIdSql() {
        return new SQL() {{
            UPDATE(tblName);
            SET("user_id=#{r.userId}");
            SET("description=#{r.description}");
            WHERE("id=#{r.id}");
        }}.toString();
    }
    public String buildSelectByRequestTutorialSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tblName);
            WHERE("is_read=#{isRead}");
        }}.toString();
    }
}
