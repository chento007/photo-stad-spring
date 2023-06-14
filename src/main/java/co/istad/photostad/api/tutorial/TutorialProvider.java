package co.istad.photostad.api.tutorial;

import org.apache.ibatis.annotations.Param;
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
            VALUES("created_by", "#{t.createdBy}");
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

    public String buildFindAllTutorialSql(@Param("name") String name) {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            if (!name.isEmpty()) {
                WHERE("name ILIKE CONCAT('%',#{name},'%')");
            }
        }}.toString();
    }

    public String buildDeleteTutorialByIdSql() {
        return new SQL() {{
            UPDATE(tblName);
            SET("is_deleted=true");
            WHERE("id=#{id}", "is_deleted=false");
        }}.toString();
    }

    public String buildUpdateTutorialByIdSql() {
        return new SQL() {{
            UPDATE(tblName);
            SET("title=#{t.title}");
            SET("name=#{t.name}");
            SET("slug=#{t.slug}");
            SET("description=#{t.description}");
            SET("thumbnail=#{t.thumbnail.id}");
            SET("html_content=#{t.htmlContent}");
            SET("updated_at=#{t.updatedAt}");
            WHERE("id=#{t.id}", "is_deleted=false");
        }}.toString();
    }

    public String buildSelectTotalViewTutorialSql() {
        return new SQL() {{
            SELECT("COUNT(view_count)");
            FROM(tblName);
            WHERE("is_deleted=false");
        }}.toString();
    }
}
