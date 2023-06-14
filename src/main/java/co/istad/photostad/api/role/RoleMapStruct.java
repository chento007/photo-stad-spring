package co.istad.photostad.api.role;

import co.istad.photostad.api.role.web.RoleDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapStruct {
    PageInfo<RoleDto> rolePageInfoToRoleDtoPageInfo(PageInfo<Role> model);
    RoleDto roleToRoleDto(Role model);
}
