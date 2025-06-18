package org.example.tlias.service;

import org.example.tlias.pojo.Comment;
import org.example.tlias.pojo.Pagebean;

import java.util.List;

public interface MessageService {
    Pagebean getMsg(Integer userId, Integer page, Integer pageSize);
    Pagebean getReply(Integer userId, Integer page, Integer pageSize);
}
