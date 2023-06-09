package co.istad.photostad.api.image;

import co.istad.photostad.api.image.web.ImageDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface ImageMapper {
    @InsertProvider(type = ImageProvider.class,method = "buildInsertImageSql")
    boolean insert(@Param("i") Image image);
    @SelectProvider(type = ImageProvider.class,method = "buildFindImageByIdSql")
    Optional<Image> findById(@Param("id")Integer id);
    @Select("SELECT EXISTS (SELECT *FROM images WHERE id=#{id})")
    boolean isIdExits(@Param("id") Integer id);
    @DeleteProvider(type = ImageProvider.class,method = "buildDeleteImageByIdSql")
    boolean deleteById(@Param("id") Integer id);
    @UpdateProvider(type = ImageProvider.class,method = "buildUpdateImage")
    boolean updateById(@Param("i") Image image);
    @SelectProvider(type = ImageProvider.class,method = "buildFindAllImageSql")
    List<Image> findAll(@Param("type") String type);
}
