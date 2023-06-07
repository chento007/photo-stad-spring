package co.istad.photostad.api.certificate;

import org.apache.ibatis.jdbc.SQL;

public class CertificateProvider {
    private final String tblName = "certificates";

    public String buildInsertCertificateSql() {
        return new SQL() {{
            INSERT_INTO(tblName);
            VALUES("uuid", "#{p.uuid}");
            VALUES("editor_json", "#{p.editorJson,typeHandler =co.istad.photostad.config.DesignJsonTypeHandler}::json");
            VALUES("created_by", "#{p.createdBy}");
            VALUES("create_date","#{p.createdDate}");
            VALUES("is_deleted", "#{p.isDeleted}");
        }}.toString();
    }
    public String buildSelectCertificateByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tblName);
            WHERE("id=#{id}");
        }}.toString();
    }
    public String buildUpdateStatusCertificateByIdSql(){
        return new SQL(){{
            UPDATE(tblName);
            SET("is_deleted=TRUE");
            WHERE("id=#{id}","");
        }}.toString();
    }
}
