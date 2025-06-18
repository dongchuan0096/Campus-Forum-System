package org.example.tlias.service.impl;

import org.apache.ibatis.annotations.Insert;
import org.example.tlias.mapper.*;
import org.example.tlias.pojo.Permission;
import org.example.tlias.pojo.Role;
import org.example.tlias.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AccountServiceimpl implements AccountService {
        @Autowired
        private AccountMapper accountMapper;

        @Autowired
        private RoleMapper roleMapper;

        @Autowired
        private PermissionMapper permissionMapper;

        @Autowired
        private RolePermissionMapper rolePermissionMapper;

        @Autowired
        private UserRolesMapper userRolesMapper;
        /*1,ROLE_USER
        2,ROLE_ADMIN
        3,ROLE_ASSESSOR
        4,ROLE_BEMUTED_USER
        5,ROLE_BEBANED_USER
        */

       /*授予新创建用户Role_User角色*/
        public Integer insertUserRole(Integer userId){
            return userRolesMapper.insertUserRole(userId,1);
        }

        /*
        * 权限管理
        *
        *
        * */

        /*禁言用户*/
        public Integer setUserRole4(Integer userId){
            return userRolesMapper.setUserRole(userId,4);
        }

        /*封号用户*/
        public Integer setUserRole5(Integer userId){
        return userRolesMapper.setUserRole(userId,5);
    }

    @Override
    public Integer setUserRole3(Integer userId) {
        return userRolesMapper.setUserRole(userId,3);
    }

    /* 将用户角色更新为ROLE_USER */
        /*取消禁言用户*/
        public Integer setUserRole1(Integer userId){
        return userRolesMapper.setUserRole(userId,1);
    }




       // 授予用户禁言权限
        public void grantMutePermission(Integer userId) {
            Role muteRole = roleMapper.findByName("ROLE_MUTE");
            if (muteRole == null) {
                muteRole = new Role();
                muteRole.setName("ROLE_MUTE");
                roleMapper.insertRole(muteRole);
            }

            Permission mutePermission = permissionMapper.findByPermission("CAN_MUTE_USERS");
            if (mutePermission == null) {
                mutePermission = new Permission();
                mutePermission.setPermission("CAN_MUTE_USERS");
                permissionMapper.insertPermission(mutePermission);
            }

            rolePermissionMapper.insertRolePermission(muteRole.getId(), mutePermission.getId());
            accountMapper.updateUserEnabled(userId, true); // Ensure user is enabled
        }

        // 撤销用户禁言权限
        public void revokeMutePermission(Integer userId) {
            Role muteRole = roleMapper.findByName("ROLE_MUTE");
            Permission mutePermission = permissionMapper.findByPermission("CAN_MUTE_USERS");

            if (muteRole != null && mutePermission != null) {
                rolePermissionMapper.deleteRolePermission(muteRole.getId(), mutePermission.getId());
            }
        }

        // 授予用户封号权限
        public void grantBanPermission(Integer userId) {
            Role banRole = roleMapper.findByName("ROLE_BAN");
            if (banRole == null) {
                banRole = new Role();
                banRole.setName("ROLE_BAN");
                roleMapper.insertRole(banRole);
            }

            Permission banPermission = permissionMapper.findByPermission("CAN_BAN_USERS");
            if (banPermission == null) {
                banPermission = new Permission();
                banPermission.setPermission("CAN_BAN_USERS");
                permissionMapper.insertPermission(banPermission);
            }

            rolePermissionMapper.insertRolePermission(banRole.getId(), banPermission.getId());
            accountMapper.updateUserEnabled(userId, false); // Disable user account
        }

        // 撤销用户封号权限
        public void revokeBanPermission(Integer userId) {
            Role banRole = roleMapper.findByName("ROLE_BAN");
            Permission banPermission = permissionMapper.findByPermission("CAN_BAN_USERS");

            if (banRole != null && banPermission != null) {
                rolePermissionMapper.deleteRolePermission(banRole.getId(), banPermission.getId());
            }
        }
    }

