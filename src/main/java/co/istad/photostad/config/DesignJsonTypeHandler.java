package co.istad.photostad.config;

import co.istad.photostad.api.json.Design;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes({Design.class})
public class DesignJsonTypeHandler extends BaseTypeHandler<Design> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Design imageJson, JdbcType jdbcType) throws SQLException {
        try {
            preparedStatement.setObject(i, objectMapper.writeValueAsString(imageJson));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Design getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if (resultSet.getString(s) != null) {
            try {
                return objectMapper.readValue(resultSet.getString(s), Design.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Design getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if (resultSet.getString(i) != null) {
            try {
                return objectMapper.readValue(resultSet.getString(i), Design.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Design getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        if (callableStatement.getString(i) != null) {
            try {
                return objectMapper.readValue(callableStatement.getString(i), Design.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
