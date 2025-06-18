package org.example.tlias.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface RolePermissionMapper {
    List<String> findPermissionsByRoleIds(List<Integer> roleIds);
    @Insert("INSERT INTO role_permissions (role_id, permission_id) VALUES (#{roleId}, #{permissionId})")
    void insertRolePermission( Integer roleId,   Integer permissionId);

    @Delete("DELETE FROM role_permissions WHERE role_id = #{roleId} AND permission_id = #{permissionId}")
    void deleteRolePermission(  Integer roleId, Integer permissionId);
}