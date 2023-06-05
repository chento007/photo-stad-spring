package co.istad.photostad.api.font;

import org.apache.ibatis.jdbc.SQL;

public class FontProvider {
    public String buildInsertSql(){
        return new SQL(){{
            INSERT_INTO("fonts");
            VALUES("id","#{f.id}");
            VALUES("full_name","#{f.fullName}");
            VALUES("family","#{f.family}");
            VALUES("post_script_name","#{f.postScriptName}");
            VALUES("preview","#{f.preview}");
            VALUES("style","#{f.style}");
            VALUES("url","#{f.url}");
            VALUES("category","#{f.category}");
            VALUES("created_at","#{f.createdAt}");
            VALUES("updated_at","#{f.updatedAt}");
            VALUES("user_id","1");
        }}.toString();
    }
    public String buildSelectSql(){
        return new SQL(){{
            SELECT("*");
            FROM("fonts");
        }}.toString();
    }
}
