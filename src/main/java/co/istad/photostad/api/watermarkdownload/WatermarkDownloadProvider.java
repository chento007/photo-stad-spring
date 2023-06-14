package co.istad.photostad.api.watermarkdownload;

import org.apache.ibatis.jdbc.SQL;

public class WatermarkDownloadProvider {
    private final String tblName = "watermarks_downloads";

    public String buildInsertWatermarkDownloadSql() {
        return new SQL() {{
            INSERT_INTO(tblName);
            VALUES("uuid", "#{w.uuid}");
            VALUES("format", "#{w.format}");
            VALUES("compression", "#{w.compression}");
            VALUES("watermark_id", "#{w.watermarkId}");
            VALUES("download_at", "#{w.downloadAt}");
            VALUES("is_deleted", "FALSE");
        }}.toString();
    }

    public String buildSelectWatermarkDownloadByIdSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            WHERE("is_deleted=false", "id=#{id}");
        }}.toString();
    }

    public String buildSelectAllWatermarkDownloadAllSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            WHERE("is_deleted=false");
        }}.toString();
    }

    public String buildDeleteWatermarkDownloadByIdSql() {
        return new SQL() {{
            UPDATE(tblName);
            SET("is_deleted=true");
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildUpdateWatermarkDownloadByIdSql() {
        return new SQL() {{
            UPDATE(tblName);
            SET("watermark_id=#{w.watermarkId}");
            SET("format=#{w.format}");
            SET("compression=#{w.compression}");
            WHERE("id=#{w.id}");
        }}.toString();
    }
}
