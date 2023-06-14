package co.istad.photostad.api.certificate;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface CertificateMapper {
    @InsertProvider(type = CertificateProvider.class, method = "buildInsertCertificateSql")
    @Result(property = "editorJson", column = "editor_json", typeHandler = co.istad.photostad.config.DesignJsonTypeHandler.class)
    boolean insert(@Param("c") Certificate certificate);

    @Results(
            id = "certificateResultMap",
            value = {
                    @Result(property = "editorJson", column = "editor_json", typeHandler = co.istad.photostad.config.DesignJsonTypeHandler.class),
                    @Result(property = "createdAt", column = "created_at"),
                    @Result(property = "createdBy", column = "created_by")
            }
    )
    @SelectProvider(type = CertificateProvider.class, method = "buildSelectCertificateByIdSql")
    Optional<Certificate> findById(@Param("id") Integer id);

    @Select("SELECT EXISTS (SELECT *FROM certificates where id=#{id})")
    boolean isIdExist(@Param("id") Integer id);

    @UpdateProvider(type = CertificateProvider.class, method = "buildUpdateStatusCertificateByIdSql")
    void deleteById(Integer id);

    @SelectProvider(type = CertificateProvider.class, method = "buildFindAllCertificateSql")
    @ResultMap("certificateResultMap")
    List<Certificate> findAll();

    @UpdateProvider(type = CertificateProvider.class, method = "buildUpdateCertificateSql")
    boolean update(@Param("c") Certificate certificate);
}
