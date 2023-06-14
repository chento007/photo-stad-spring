package co.istad.photostad.api.watermarkdownload;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface WatermarkDownloadMapper {
    @InsertProvider(type = WatermarkDownloadProvider.class, method = "buildInsertWatermarkDownloadSql")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insert(@Param("w") WatermarkDownload watermarkDownload);

    @SelectProvider(type = WatermarkDownloadProvider.class, method = "buildSelectWatermarkDownloadByIdSql")
    @Results(id = "WatermarkDownloadResultMap", value = {@Result(property = "watermarkId", column = "watermark_id"), @Result(property = "downloadAt", column = "download_at")})
    Optional<WatermarkDownload> findById(@Param("id") Integer id);

    @ResultMap("WatermarkDownloadResultMap")
    @SelectProvider(type = WatermarkDownloadProvider.class, method = "buildSelectAllWatermarkDownloadAllSql")
    List<WatermarkDownload> findAll();

    @UpdateProvider(type = WatermarkDownloadProvider.class, method = "buildDeleteWatermarkDownloadByIdSql")
    boolean deleteById(@Param("id") Integer id);


    @Select("SELECT EXISTS(SELECT *FROM watermarks_downloads WHERE id=#{id})")
    boolean isExistById(@Param("id") Integer id);

    @UpdateProvider(type = WatermarkDownloadProvider.class,method = "buildUpdateWatermarkDownloadByIdSql")
    boolean updateById(@Param("w") WatermarkDownload watermarkDownload);

}
