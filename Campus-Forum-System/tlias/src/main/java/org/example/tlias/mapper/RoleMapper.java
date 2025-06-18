package org.example.tlias.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Delete;
import org.example.tlias.pojo.Role;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Select("SELECT * FROM roles WHERE name = #{roleName}")
    Role findByName(String roleName);

    @Insert("INSERT INTO roles (name) VALUES (#{name})")
    void insertRole(Role role);

    @Delete("DELETE FROM roles WHERE id = #{roleId}")
    void deleteRole(Integer roleId);

    @Select("SELECT r.name " +
            "FROM roles r " +
            "JOIN user_roles ur ON r.id = ur.role_id " +
            "WHERE ur.user_id = #{userId}")
    List<String> findRolesByUserId(Integer userId);
}
