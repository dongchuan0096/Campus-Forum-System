package org.example.tlias.service.impl;

import org.example.tlias.mapper.AccountMapper;
import org.example.tlias.mapper.RoleMapper;
import org.example.tlias.mapper.RolePermissionMapper;
import org.example.tlias.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = accountMapper.findByUsername(username);
        if (user == null) {
            System.out.println("//////////////////////////////////////////////////");
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("//////////////////////////////////////////////////");
        // 获取用户的角色
        List<String> roles = roleMapper.findRolesByUserId(user.getUserId());
        // 获取角色对应的权限
        List<String> permissions = rolePermissionMapper.findPermissionsByRoleIds(
                roles.stream().map(roleName -> roleMapper.findByName(roleName).getId()).collect(Collectors.toList())
        );

        // 创建角色和权限的 GrantedAuthority 对象
        List<SimpleGrantedAuthority> authorities = Stream.concat(
                roles.stream().map(role -> new SimpleGrantedAuthority(role)),
                permissions.stream().map(SimpleGrantedAuthority::new)
        ).collect(Collectors.toList());
        System.out.println(authorities);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}

