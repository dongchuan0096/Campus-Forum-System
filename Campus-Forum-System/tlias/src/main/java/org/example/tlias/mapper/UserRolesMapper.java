package org.example.tlias.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserRolesMapper {
    @Insert("insert into user_roles (user_id, role_id) VALUE (#{userId},#{roleId})")
    Integer insertUserRole(Integer userId,Integer roleId);
    @Update("update user_roles set role_id = #{roleId} where user_id = #{userId}")
    Integer setUserRole(Integer userId,Integer roleId);
}
