package org.example.tlias.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.tlias.mapper.RoleMapper;
import org.example.tlias.mapper.UserMapper;
import org.example.tlias.pojo.Pagebean;
import org.example.tlias.pojo.User;
import org.example.tlias.service.AccountService;
import org.example.tlias.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AccountService accountService;
    @Autowired
    RoleMapper roleMapper;
    /*
     * 实现user登录
     * @Return User
     * @Param User
     * */
    @Override
    public User userLogin(User user) {
        return userMapper.getByIdAndPassword(user);
    }
    @Override
    public Integer insertUser(User user) {
        user.setAvatarUrl(null);
        user.setUserId(null);
        LocalDateTime dateTime=LocalDateTime.now();
        user.setCreatedAt(dateTime);
        user.setUpdatedAt(dateTime);
        userMapper.insertUser(user);
        return  accountService.insertUserRole(user.getUserId());
    }

    @Override
    public Integer updateUser(User user) {
        user.setUpdatedAt(LocalDateTime.now());
        return userMapper.updateUser(user);
    }

    @Override
    public User getByID(Integer userId) {
        User user = userMapper.getByID(userId);
        if (user.getIntroduction() == null) {
            user.setIntroduction("还没有简介");
        }
        List<String> roles = roleMapper.findRolesByUserId(user.getUserId()); // 获取用户角色

        // 检查是否包含ROLE_USER角色
        boolean hasRoleUser = roles.stream().anyMatch(role -> "ROLE_USER".equals(role));
        if (hasRoleUser) {
            // 移除密码并设置第一个角色
            user.setPassword(null);
            if (!roles.isEmpty()) {
                user.setRole(roles.get(0));
            }

        }
        return user;
    }

    @Override
    public Integer insertUserAvater(Integer userId, String avatarUrl) {

        User user = userMapper.getByID(userId);
        System.out.println("//////////////////////");
        System.out.println(user);
        // 计算当前时间加上 7 天后的 LocalDateTime 对象
        LocalDateTime sevenDaysLater = user.getUpdatedAt().plus(7, ChronoUnit.DAYS);
        if(user.getUpdatedAt().isBefore(LocalDateTime.now())&&user.getAvatarUrl()!=null){
            return 0;
        }
        else{
            return userMapper.insertUserAvater(userId,avatarUrl,LocalDateTime.now());
        }
    }
    @Override
    public Pagebean getUsers(User user, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<User> users = userMapper.getUsers(user); // 获取所有用户
        List<User> filteredUsers = new ArrayList<>(); // 存储满足条件的用户

        for (User user1 : users) {
            List<String> roles = roleMapper.findRolesByUserId(user1.getUserId()); // 获取用户角色

            // 检查是否包含ROLE_USER角色
            boolean hasRoleUser = roles.stream().anyMatch(role -> "ROLE_USER".equals(role));
            if (hasRoleUser) {
                // 移除密码并设置第一个角色
                user1.setPassword(null);
                if (!roles.isEmpty()) {
                    user1.setRole(roles.get(0));
                }
                // 添加到结果列表中
                filteredUsers.add(user1);
            }
        }
        PageInfo<User> p = new PageInfo<>(filteredUsers);
        Pagebean pagebean = new Pagebean(p.getTotal(), p.getList());
        /*        for (Post post : list) {
            List<String> urls = postMapper.getMediaByPostId(post.getPostId());
            post.setUrl(urls);
        }*/
        return pagebean;
    }

    @Override
    public Pagebean getUsersAdmin(User user, Integer page, Integer pageSize, String userRole) {
        PageHelper.startPage(page, pageSize);
        List<User> users = userMapper.getUsers(user); // 获取所有用户
        List<User> filteredUsers = new ArrayList<>(); // 存储满足条件的用户

        for (User user1 : users) {
            List<String> roles = roleMapper.findRolesByUserId(user1.getUserId()); // 获取用户角色

            // 检查是否包含ROLE_USER角色
            boolean hasRoleUser = true;
            if(userRole!=null&&userRole!=""){
                hasRoleUser =  roles.stream().anyMatch(role -> userRole.equals(role));
                System.out.println("//////////////////////////////////////////////////////////");
            }
            if (hasRoleUser) {
                // 移除密码并设置第一个角色
                user1.setPassword(null);
                if (!roles.isEmpty()) {
                    user1.setRole(roles.get(0));
                }
                // 添加到结果列表中
                filteredUsers.add(user1);
            }
        }

        if(filteredUsers.isEmpty()&&!users.isEmpty()){
            return this.getUsersAdmin(user,++page,pageSize,userRole);
        }
        PageInfo<User> p = new PageInfo<>(filteredUsers);
        Pagebean pagebean = new Pagebean(p.getTotal(), p.getList());
        return pagebean;
    }


    /*获得所有被禁言和被封号用户*/
public Pagebean getBanedUsers(User user, Integer page, Integer pageSize) {
    PageHelper.startPage(page, pageSize);
    List<User> users = userMapper.getUsers(user); // 获取所有用户
    List<User> filteredUsers = new ArrayList<>(); // 存储满足条件的用户

    for (User user1 : users) {
        List<String> roles = roleMapper.findRolesByUserId(user1.getUserId()); // 获取用户角色

        // 检查是否包含ROLE_USER角色
        boolean hasRoleUser = roles.stream().anyMatch(role -> "ROLE_BEMUTED_USER".equals(role) || "ROLE_BEBANED_USER".equals(role));

        if (hasRoleUser) {
            // 移除密码并设置第一个角色
            user1.setPassword(null);
            if (!roles.isEmpty()) {
                user1.setRole(roles.get(0));
            }
            // 添加到结果列表中
            filteredUsers.add(user1);
        }

    }
    if(filteredUsers.isEmpty()&&!users.isEmpty()){
       return this.getBanedUsers(user,++page,pageSize);
    }
    PageInfo<User> p = new PageInfo<>(filteredUsers);
    Pagebean pagebean = new Pagebean(p.getTotal(), p.getList());
        /*        for (Post post : list) {
            List<String> urls = postMapper.getMediaByPostId(post.getPostId());
            post.setUrl(urls);
        }*/
    return pagebean;
}

}