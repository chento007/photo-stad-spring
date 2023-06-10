package co.istad.photostad.api.tutorials;

import co.istad.photostad.api.image.Image;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface TutorialMapper {
    @InsertProvider(type = TutorialProvider.class, method = "buildInsertTutorialSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    boolean insert(@Param("t") Tutorial tutorial);

    @SelectProvider(type = TutorialProvider.class, method = "buildFindTutorialByIdSql")
    @Results(
            id = "tutorialResultMap",
            value = {
                    @Result(column = "created_at", property = "createdAt"),
                    @Result(column = "view_count", property = "viewCount"),
                    @Result(column = "created_by",property = "createdBy"),
                    @Result(column = "content_html",property = "contentHtml"),
                    @Result(column = "id", property = "thumbnail", one = @One(select = "findImageById"))
            }
    )
    Optional<Tutorial> findById(Integer id);

    @Select("SELECT *FROM images WHERE id=#{id}")
    Image findImageById(@Param("id") Integer id);
}
