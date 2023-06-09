package co.istad.photostad.api.user;

import co.istad.photostad.api.auth.web.RegisterDto;
import co.istad.photostad.api.user.web.ModifyUserDto;
import co.istad.photostad.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.sql.Timestamp;

@Mapper(componentModel = "spring")
public interface UserMapStruct {
    User registerDtoToUser(RegisterDto model);
    UserDto userToUserDto(User model);
    @Mapping(source = "avatar", target = "avatar.id")
    User userModifyDtoToUser(ModifyUserDto model);
    PageInfo<UserDto> userPageInfoToUserDtoPageinfo(PageInfo<User> model);
}
