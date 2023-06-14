package co.istad.photostad.api.feature;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface FeatureMapper {
    @InsertProvider(type = FeatureProvider.class, method = "buildInsertFeatureSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    boolean insert(@Param("f") Feature feature);

    @SelectProvider(type = FeatureProvider.class, method = "buildSelectFeatureByIdSql")
    @Result(property = "sample", column = "sample", typeHandler = co.istad.photostad.config.DesignJsonTypeHandler.class)
    Optional<Feature> findById(@Param("id") Integer id);

    @SelectProvider(type = FeatureProvider.class, method = "buildFindAllFeatureSql")
    @Result(property = "sample", column = "sample", typeHandler = co.istad.photostad.config.DesignJsonTypeHandler.class)
    List<Feature> findAll(@Param("name") String name);

    @UpdateProvider(type = FeatureProvider.class, method = "buildUpdateFeatureSql")
    boolean update(@Param("f") Feature feature);

    @Select("SELECT EXISTS(SELECT *FROM features WHERE id=#{id})")
    boolean isFeatureIdExist(@Param("id") Integer id);
}
