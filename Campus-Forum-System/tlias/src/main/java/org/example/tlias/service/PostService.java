package org.example.tlias.service;

import org.example.tlias.mapper.PostMapper;
import org.example.tlias.pojo.Pagebean;
import org.example.tlias.pojo.Post;
import org.example.tlias.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PostService {
    /*
    * 添加笔记
    * */
    Boolean insertPost(Post post);
    Boolean updatePost(Post post);
    Pagebean getPosts(Integer userId, Integer page, Integer pagesize);
    Post  getPost( Integer postId);
     List<Post> getWorks(Integer userId ,Integer audi);
    List<Integer> getWorksId(Integer userId);
     Integer postLike(Integer postId, Integer userId);
    boolean ispostLiked(Integer postId, Integer userId);

    Post getNewPost(Integer userId);
    Pagebean searchPosts(Post post,Integer page,Integer pageSize);
    Integer audi(Integer postId,Integer audi);
    List<Post> getAllPost();
    Integer deletePost(Integer postId);
}
