package co.istad.photostad.api.tutorials;

import org.apache.ibatis.jdbc.SQL;

public class TutorialProvider {
    private final String tblName = "tutorials";

    public String buildInsertTutorialSql() {
        return new SQL() {{
            INSERT_INTO(tblName);
            VALUES("uuid", "#{t.uuid}");
            VALUES("title", "#{t.title}");
            VALUES("view_count", "0");
            VALUES("thumbnail", "#{t.thumbnail.id}");
            VALUES("created_at", "#{t.createdAt}");
            VALUES("created_by", "#{t.createdBy.id}");
            VALUES("is_deleted", "FALSE");
        }}.toString();
    }

    public String buildFindTutorialByIdSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            WHERE("id=#{id}", "is_deleted=false");
        }}.toString();
    }
}
