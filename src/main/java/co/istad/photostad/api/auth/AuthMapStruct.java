package co.istad.photostad.api.auth;

import co.istad.photostad.api.auth.web.RegisterDto;
import co.istad.photostad.api.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapStruct {
    User registerDtoToUser(RegisterDto model);
}
