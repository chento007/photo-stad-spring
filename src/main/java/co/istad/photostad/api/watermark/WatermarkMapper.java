package co.istad.photostad.api.watermark;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface WatermarkMapper {
    @InsertProvider(type = WatermarkProvider.class, method = "buildInsertWatermarkSql")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insert(@Param("w") Watermark watermark);

    @Results(
            id = "watermarkResultMap",
            value = {
                    @Result(property = "editorJson", column = "editor_json", typeHandler = co.istad.photostad.config.DesignJsonTypeHandler.class),
                    @Result(property = "createdAt", column = "created_at"),
                    @Result(property = "createdBy", column = "created_by")
            }
    )
    @SelectProvider(type = WatermarkProvider.class, method = "buildSelectWatermarkByIdSql")
    Optional<Watermark> findById(@Param("id") Integer id);

    @UpdateProvider(type = WatermarkProvider.class, method = "buildUpdateStatusWatermarkByIdSql")
    boolean deleteById(@Param("id") Integer id);


    @Select("SELECT EXISTS (SELECT *FROM watermarks WHERE id=#{id} AND is_deleted=false)")
    boolean isWatermarkIdExist(@Param("id") Integer id);

    @SelectProvider(type = WatermarkProvider.class, method = "buildFindAllWatermarkSql")
    @ResultMap("watermarkResultMap")
    List<Watermark> findAll();

    @UpdateProvider(type = WatermarkProvider.class, method = "buildUpdateWatermarkSql")
    boolean update(@Param("w") Watermark watermark);

    @SelectProvider(type = WatermarkProvider.class,method = "buildSelectCountWatermarkAndCertificateSql")
    Integer dailyEditCount();
}
