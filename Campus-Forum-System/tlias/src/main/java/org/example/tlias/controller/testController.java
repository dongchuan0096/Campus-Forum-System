package org.example.tlias.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class testController {

    // 模拟的数据：一个简单的用户列表
    private List<User> users = List.of(
            new User(1, "John Doe", "john.doe@example.com"),
            new User(2, "Jane Smith", "jane.smith@example.com"),
            new User(3, "Alice Brown", "alice.brown@example.com")
    );

    // 处理GET请求，返回用户列表数据
    @GetMapping("/test")
    public List<User> getUsers() {
        return users;
    }

    // 内部User类用于返回数据
    public static class User {
        private int id;
        private String name;
        private String email;

        // 构造器
        public User(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        // Getter 和 Setter 方法
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
