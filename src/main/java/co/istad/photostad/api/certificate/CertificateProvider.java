package co.istad.photostad.api.certificate;

import org.apache.ibatis.jdbc.SQL;

public class CertificateProvider {
    private final String tblName = "certificates";

    public String buildInsertCertificateSql() {
        return new SQL() {{
            INSERT_INTO(tblName);
            VALUES("uuid", "#{c.uuid}");
            VALUES("editor_json", "#{c.editorJson,typeHandler =co.istad.photostad.config.DesignJsonTypeHandler}::json");
            VALUES("created_by", "#{c.createdBy}");
            VALUES("created_at", "#{c.createdAt}");
            VALUES("is_deleted", "FALSE");
        }}.toString();
    }

    public String buildSelectCertificateByIdSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            WHERE("id=#{id}","is_deleted=false");
        }}.toString();
    }

    public String buildUpdateStatusCertificateByIdSql() {
        return new SQL() {{
            UPDATE(tblName);
            SET("is_deleted=TRUE");
            WHERE("id=#{id}", "is_deleted=false");
        }}.toString();
    }

    public String buildFindAllCertificateSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            WHERE("is_deleted=false");
        }}.toString();
    }

    public String buildUpdateCertificateSql() {
        return new SQL() {{
            UPDATE(tblName);
            SET("created_by=#{c.createdBy}");
            SET("editor_json =#{c.editorJson,typeHandler =co.istad.photostad.config.DesignJsonTypeHandler}::json");
            WHERE("id=#{c.id}");
        }}.toString();
    }
}
