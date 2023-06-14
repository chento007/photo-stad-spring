package co.istad.photostad.api.feature;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class FeatureProvider {
    private final String tblName = "features";

    public String buildInsertFeatureSql() {
        return new SQL() {{
            INSERT_INTO(tblName);
            VALUES("name", "#{f.name}");
            VALUES("sample", "#{f.sample,typeHandler =co.istad.photostad.config.DesignJsonTypeHandler}::json");
        }}.toString();
    }

    public String buildSelectFeatureByIdSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildFindAllFeatureSql(@Param("name") String name) {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            if (!name.isEmpty()) {
                WHERE("name ILIKE CONCAT('%',#{name},'%')");
            }
        }}.toString();
    }

    public String buildUpdateFeatureSql() {
        return new SQL() {{
            UPDATE(tblName);
            SET("name=#{f.name}");
            SET("sample=#{f.sample,typeHandler =co.istad.photostad.config.DesignJsonTypeHandler}::json");
            WHERE("id=#{f.id}");
        }}.toString();
    }
}
