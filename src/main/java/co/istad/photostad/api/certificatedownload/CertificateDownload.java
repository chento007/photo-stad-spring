package co.istad.photostad.api.certificatedownload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CertificateDownload {
    private Integer id;
    private String uuid;
    private Integer certificateId;
    private String format;
    private String compression;
    private Timestamp downloadAt;
    private Boolean isDeleted;
}
