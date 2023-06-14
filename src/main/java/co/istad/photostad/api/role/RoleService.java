package co.istad.photostad.api.role;

import co.istad.photostad.api.role.web.RoleDto;
import com.github.pagehelper.PageInfo;

public interface RoleService {
    PageInfo<RoleDto> selectAllRole(int page,int limit,String name);
    RoleDto selectRoleById(Integer id);
}
