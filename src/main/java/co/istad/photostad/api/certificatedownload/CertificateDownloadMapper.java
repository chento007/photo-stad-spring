package co.istad.photostad.api.certificatedownload;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface CertificateDownloadMapper {
    @InsertProvider(type = CertificateDownloadProvider.class, method = "buildInsertCertificateDownloadSql")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insert(@Param("c") CertificateDownload certificateDownload);


    @SelectProvider(type = CertificateDownloadProvider.class, method = "buildSelectCertificateDownloadByIdSql")
    @Results(
            id = "certificateDownloadResultMap",
            value = {
                    @Result(column = "download_at", property = "downloadAt"),
                    @Result(column = "certificate_id", property = "certificateId")
            }
    )
    Optional<CertificateDownload> findById(@Param("id") Integer id);

    @Select("SELECT EXISTS( SELECT * FROM certificates_downloads WHERE id=#{id} )")
    boolean isIdExist(@Param("id") Integer id);

    @UpdateProvider(type = CertificateDownloadProvider.class,method = "buildUpdateCertificateDownloadByIdSql")
    boolean updateById(@Param("c") CertificateDownload certificateDownload);

    @SelectProvider(type = CertificateDownloadProvider.class,method = "buildSelectAllCertificateDownloadSql")
    @ResultMap("certificateDownloadResultMap")
    List<CertificateDownload> findAll();

    @UpdateProvider(type = CertificateDownloadProvider.class,method = "buildDeleteCertificateDownloadSql")
    boolean deletedById(@Param("id") Integer id);
}
