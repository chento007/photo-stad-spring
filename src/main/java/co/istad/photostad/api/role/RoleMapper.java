package co.istad.photostad.api.role;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface RoleMapper {
    @SelectProvider(type = RoleProvider.class, method = "buildSelectAllRoleSql")
    List<Role> findAll(@Param("name") String name);
    @SelectProvider(type = RoleProvider.class,method = "buildSelectRoleByIdSql")
    Optional<Role> findById(@Param("id") Integer id);
}
