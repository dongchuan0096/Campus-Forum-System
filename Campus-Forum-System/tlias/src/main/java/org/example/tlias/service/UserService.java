package org.example.tlias.service;

import org.example.tlias.pojo.Pagebean;
import org.example.tlias.pojo.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {
    /*
    * user登录
    * @Return User
    * @Param User
    * */
    User userLogin(User user);
    Integer insertUser(User user);

    Integer updateUser(User user);
    User getByID(Integer userId);
    Integer insertUserAvater(Integer userId,  String avatarUrl);

    Pagebean getUsers(User user, Integer page, Integer pageSize);
    Pagebean getUsersAdmin(User user, Integer page, Integer pageSize,String userRole);
    Pagebean getBanedUsers(User user, Integer page, Integer pageSize);
}
