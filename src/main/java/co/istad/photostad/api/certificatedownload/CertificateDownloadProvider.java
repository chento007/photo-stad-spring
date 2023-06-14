package co.istad.photostad.api.certificatedownload;

import org.apache.ibatis.jdbc.SQL;

public class CertificateDownloadProvider {
    private final String tblName = "certificates_downloads";

    public String buildInsertCertificateDownloadSql() {
        return new SQL() {{
            INSERT_INTO(tblName);
            VALUES("uuid", "#{c.uuid}");
            VALUES("format", "#{c.format}");
            VALUES("compression", "#{c.compression}");
            VALUES("certificate_id", "#{c.certificateId}");
            VALUES("download_at", "#{c.downloadAt}");
            VALUES("is_deleted", "FALSE");
        }}.toString();
    }

    public String buildSelectCertificateDownloadByIdSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            WHERE("is_deleted=false", "id=#{id}");
        }}.toString();
    }

    public String buildUpdateCertificateDownloadByIdSql() {
        return new SQL() {{
            UPDATE(tblName);
            SET("certificate_id=#{c.certificateId}");
            SET("format=#{c.format}");
            SET("compression=#{c.compression}");
            WHERE("id=#{c.id}");
        }}.toString();
    }

    public String buildSelectAllCertificateDownloadSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            WHERE("is_deleted=false");
        }}.toString();
    }

    public String buildDeleteCertificateDownloadSql() {
        return new SQL() {{
            UPDATE(tblName);
            SET("is_deleted=true");
            WHERE("id=#{id}");
        }}.toString();
    }
}
