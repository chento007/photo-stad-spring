package co.istad.photostad.api.watermarkdownload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WatermarkDownload {
    private Integer id;
    private String uuid;
    private Integer watermarkId;
    private String format;
    private String compression;
    private Timestamp downloadAt;
    private Boolean isDeleted;
}
