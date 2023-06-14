package co.istad.photostad.api.watermark;

import org.apache.ibatis.jdbc.SQL;

public class WatermarkProvider {
    private final String tblName = "watermarks";

    public String buildInsertWatermarkSql() {
        return new SQL() {{
            INSERT_INTO(tblName);
            VALUES("uuid", "#{w.uuid}");
            VALUES("editor_json", "#{w.editorJson,typeHandler =co.istad.photostad.config.DesignJsonTypeHandler}::json");
            VALUES("created_by", "#{w.createdBy}");
            VALUES("created_at", "#{w.createdAt}");
            VALUES("is_deleted", "FALSE");
        }}.toString();
    }

    public String buildSelectWatermarkByIdSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            WHERE("id=#{id}", "is_deleted=false");
        }}.toString();
    }

    public String buildUpdateStatusWatermarkByIdSql() {
        return new SQL() {{
            UPDATE(tblName);
            SET("is_deleted=TRUE");
            WHERE("id=#{id}", "is_deleted=false");
        }}.toString();
    }

    public String buildFindAllWatermarkSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            WHERE("is_deleted=false");
        }}.toString();
    }

    public String buildUpdateWatermarkSql() {
        return new SQL() {{
            UPDATE(tblName);
            SET("created_by=#{w.createdBy}");
            SET("editor_json =#{w.editorJson,typeHandler =co.istad.photostad.config.DesignJsonTypeHandler}::json");
            WHERE("id=#{w.id}");
        }}.toString();
    }

    public String buildSelectCountWatermarkAndCertificateSql() {
        return new SQL() {{
            SELECT("SUM(count) AS total_count");
            FROM("(SELECT COUNT(*) AS count FROM certificates WHERE created_at::date = CURRENT_DATE" +
                    " UNION ALL " +
                    "SELECT COUNT(*) AS count FROM watermarks WHERE created_at::date = CURRENT_DATE) AS subquery");
        }}.toString();
    }

}
