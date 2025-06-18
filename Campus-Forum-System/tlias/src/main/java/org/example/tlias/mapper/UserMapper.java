package org.example.tlias.mapper;

import org.apache.ibatis.annotations.*;
import org.example.tlias.pojo.User;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserMapper {
    /*
     * 通过username和password查询user
     * @Return User
     * @Param User
     * */
    @Select("select * from users where username=#{username} and password = #{password}")
    User getByIdAndPassword(User user);

    /*
     * 注册，插入user
     * */
    @Insert("INSERT INTO users (username, email, password, avatar_url, created_at, updated_at) VALUES (#{username}, #{email}, #{password}, #{avatarUrl}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    Integer insertUser(User user);

    /*
    *
    * 更新user
    * */
    @Update("update users set username=#{username},introduction=#{introduction},updated_at = #{updatedAt} where user_id = #{userId}")
    Integer updateUser(User user);
    /*
     * 通过userID查询user信息，并返回所有信息
     *
     * */
    @Select("select * from user_info where user_id = #{userId}")
    User getByID(Integer userId);

    @Insert("update users set avatar_url = #{avatarUrl},updated_at = #{updatedAt} where user_id = #{userId}  ")
    Integer insertUserAvater(Integer userId, String avatarUrl, LocalDateTime updatedAt);


    List<User> getUsers(User user);
}
