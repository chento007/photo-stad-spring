package co.istad.photostad.api.tutorial;

import co.istad.photostad.api.image.Image;
import org.apache.ibatis.annotations.*;

import java.util.List;
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
                    @Result(column = "created_by", property = "createdBy"),
                    @Result(column = "html_content", property = "htmlContent"),
                    @Result(column = "thumbnail", property = "thumbnail", one = @One(select = "findImageById"))
            }
    )
    Optional<Tutorial> findById(Integer id);

    @ResultMap("tutorialResultMap")
    @SelectProvider(type = TutorialProvider.class, method = "buildFindAllTutorialSql")
    List<Tutorial> findAll(@Param("name") String name);

    @Select("SELECT *FROM images WHERE id=#{id}")
    Image findImageById(@Param("id") Integer id);

    @Select("SELECT EXISTS (SELECT *FROM tutorials WHERE id=${id} AND is_deleted=false)")
    boolean isTutorialIdExits(@Param("id") Integer id);

    @UpdateProvider(type = TutorialProvider.class, method = "buildDeleteTutorialByIdSql")
    boolean deleteTutorialById(@Param("id") Integer id);

    @UpdateProvider(type = TutorialProvider.class, method = "buildUpdateTutorialByIdSql")
    boolean updateTutorialById(@Param("t") Tutorial tutorial);


    @SelectProvider(type = TutorialProvider.class,method = "buildSelectTotalViewTutorialSql")
    Integer selectCountByViewCount();
}
