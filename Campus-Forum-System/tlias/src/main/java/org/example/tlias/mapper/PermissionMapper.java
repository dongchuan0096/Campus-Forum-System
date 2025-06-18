package org.example.tlias.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Delete;
import org.example.tlias.pojo.Permission;

@Mapper
public interface PermissionMapper {

    @Select("SELECT * FROM permissions WHERE permission = #{permission}")
    Permission findByPermission(String permission);

    @Insert("INSERT INTO permissions (permission) VALUES (#{permission})")
    void insertPermission(Permission permission);

    @Delete("DELETE FROM permissions WHERE id = #{permissionId}")
    void deletePermission(Long permissionId);
}