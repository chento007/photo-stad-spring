package co.istad.photostad.api.user;

import co.istad.photostad.api.image.Image;
import co.istad.photostad.api.user.web.ModifyUserDto;
import co.istad.photostad.api.user.web.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Service
public class UserServiceImp implements UserService {
    private final UserMapper userMapper;
    private final UserMapStruct userMapStruct;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public UserDto selectEmail(String email) {
        User user = userMapper.findEmail(email).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with email = %s is not found", email)
                )
        );
        System.out.println(user);
        return userMapStruct.userToUserDto(user);
    }

    @Override
    public PageInfo<UserDto> selectAll(int page, int limit, String name ){
        PageInfo<User> userPageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(
                () -> userMapper.findAll(name)
        );
        if (!name.isEmpty() && userMapper.findAll(name).isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("User with name %s is not found", name)
            );
        }
        return userMapStruct.userPageInfoToUserDtoPageinfo(userPageInfo);
    }

    @Override
    public UserDto selectById(Integer id) {
        User user = userMapper.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with id %s id not found", id)
                )
        );
        return userMapStruct.userToUserDto(user);
    }

    @Override
    public Integer deleteUpdateStatusById(Integer id) {
        if (userMapper.findById(id).isPresent()) {
            if (userMapper.deleteByUpdateIsDeletedById(id)) {
                return id;
            }
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Delete user with %d is fail", id)
            );
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("User with id %d is not found", id)
        );
    }

    @Override
    public UserDto insertUser(Integer id, ModifyUserDto modifyUserDto) {
        if (userMapper.findById(id).isPresent()) {
            //check
            User user=userMapStruct.userModifyDtoToUser(modifyUserDto);
            user.setAvatar(userMapper.findImageById(user.getAvatar().getId()));
            user.setId(id);
            System.out.println(user);
            if(userMapper.update(user)){
                return this.selectById(id);
            }
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("insert user with %d is fail", id)
            );
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("User with id %d is not found", id)
        );
    }
}
