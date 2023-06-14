package co.istad.photostad.api.user;

import co.istad.photostad.api.user.web.ModifyUserDto;
import co.istad.photostad.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;

public interface UserService {
    /**
     * select user by email
     * @param email : search by user's email
     * @return UserDto is data to response user
     */
    UserDto selectEmail(String email);

    /**
     * select user with pagination and according to name if name has value it will search
     * @param page : location page
     * @param limit : size of page
     * @param name : username
     * @return pagination
     */

    PageInfo<UserDto> selectAll(int page, int limit, String name);

    /**
     * search user by user ID
     * @param id belong to user
     * @return UserDto is data to response user
     */
    UserDto selectById(Integer id);
    Integer deleteUpdateStatusById(Integer id);
     UserDto insertUser (Integer id,ModifyUserDto modifyUserDto);
}
