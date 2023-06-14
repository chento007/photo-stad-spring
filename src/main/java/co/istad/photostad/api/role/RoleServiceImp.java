package co.istad.photostad.api.role;

import co.istad.photostad.api.role.web.RoleDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class RoleServiceImp implements RoleService {
    private final RoleMapper roleMapper;
    private final RoleMapStruct roleMapStruct;
    @Override
    public PageInfo<RoleDto> selectAllRole(int page, int limit, String name) {
        PageInfo<Role> rolePageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(
                () -> roleMapper.findAll(name)
        );
        if (!name.isEmpty() && roleMapper.findAll(name).size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Role with name %s is not found", name)
            );
        }
        return roleMapStruct.rolePageInfoToRoleDtoPageInfo(rolePageInfo);
    }

    @Override
    public RoleDto selectRoleById(Integer id) {
        Role role=roleMapper.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("role with %d is not found",id)
                )
        );
        return roleMapStruct.roleToRoleDto(role);
    }
}
