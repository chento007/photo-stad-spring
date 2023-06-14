package co.istad.photostad.api.role.web;

import co.istad.photostad.api.role.RoleService;
import co.istad.photostad.base.BaseRest;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/roles")
public class RoleRestController {
    private final RoleService roleService;

    @GetMapping
    public BaseRest<?> selectAllRole(@RequestParam(required = false, name = "page", defaultValue = "1") int page,
                                     @RequestParam(required = false, name = "limit", defaultValue = "20") int limit,
                                     @RequestParam(required = false, name = "name", defaultValue = "") String name) {
        PageInfo<RoleDto> resultFindAll = roleService.selectAllRole(page, limit, name);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("role has been found success")
                .data(resultFindAll)
                .build();
    }
    @GetMapping("/{id}")
    public BaseRest<?> selectRoleById(@PathVariable("id") Integer id) {
        RoleDto resultFindById=roleService.selectRoleById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("role has been found success")
                .data(resultFindById)
                .build();
    }
}
