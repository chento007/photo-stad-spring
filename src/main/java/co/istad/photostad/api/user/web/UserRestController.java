package co.istad.photostad.api.user.web;

import co.istad.photostad.api.user.UserService;
import co.istad.photostad.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    private final UserService userService;

    @GetMapping("/email")
    public BaseRest<?> selectEmail(@RequestParam String email) {
        UserDto userDto = userService.selectEmail(email);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("email has been found")
                .data(userDto)
                .build();
    }

    @GetMapping
    public BaseRest<?> selectAll(@RequestParam(required = false, name = "page", defaultValue = "1") int page,
                                 @RequestParam(required = false, name = "limit", defaultValue = "20") int limit,
                                 @RequestParam(required = false, name = "name", defaultValue = "") String name
    ) {
        PageInfo<UserDto> userDtoPageInfo = userService.selectAll(page, limit, name);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User has been found")
                .data(userDtoPageInfo)
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> selectById(@PathVariable("id") Integer id) {
        UserDto userDto = userService.selectById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User has been found")
                .data(userDto)
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseRest<?> deleteByID(@PathVariable("id") Integer userId) {
        Integer id = userService.deleteUpdateStatusById(userId);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User has been found")
                .data(id)
                .build();
    }

    @PutMapping("/{id}")
    public BaseRest<?> insertUser(@PathVariable("id") Integer id,@Valid @RequestBody ModifyUserDto modifyUserDto) {
        UserDto userDto = userService.insertUser(id,modifyUserDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User has been insert success")
                .data(userDto)
                .build();
    }
}
