package org.example.tlias.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.tlias.pojo.User;

@Mapper
public interface AccountMapper {
    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(String username);
    @Select("select * from users where user_id = #{userId}")
    User findByUserId(Integer userId);
    @Select("SELECT * FROM users WHERE user_id = #{userId}")
    User findById(Integer userId);

    @Update("UPDATE users SET enabled = #{enabled} WHERE user_id = #{userId}")
    void updateUserEnabled(Integer userId, boolean enabled);
}
