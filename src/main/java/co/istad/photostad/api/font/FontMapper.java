package co.istad.photostad.api.font;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FontMapper {
    @InsertProvider(type = FontProvider.class, method = "buildInsertSql")
    void save(@Param("f") Font font);

    @Results(
            id = "fontMapper",
            value = {
                    @Result(column = "full_name", property = "fullName"),
                    @Result(column = "post_script_name", property = "postScriptName"),
                    @Result(column = "created_at", property = "createdAt"),
                    @Result(column = "updated_at", property = "updatedAt"),
                    @Result(column = "user_id", property = "userId")
            }
    )
    @SelectProvider(type = FontProvider.class, method = "buildSelectSql")
    List<Font> selectAll();
}
