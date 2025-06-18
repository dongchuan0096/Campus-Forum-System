package org.example.tlias.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.tlias.mapper.LikesLogMapper;
import org.example.tlias.mapper.PostMapper;
import org.example.tlias.pojo.Emp;
import org.example.tlias.pojo.Pagebean;
import org.example.tlias.pojo.Post;
import org.example.tlias.pojo.PostMedia;
import org.example.tlias.service.LikesLogService;
import org.example.tlias.service.PostService;
import org.example.tlias.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostMapper postMapper;
    @Autowired
    LikesLogService likesLogService;

    /*
     * 添加帖子
     * */
    @Override
    @Transactional
    public Boolean insertPost(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        post.setAudi(0);
        Integer isinsertPost = postMapper.insertPost(post);
        PostMedia postMedia = new PostMedia();
        if (isinsertPost == 1) {
            for (String url : post.getUrl()) {
                postMedia.setUrl(url);
                postMedia.setPostId(post.getPostId());
                postMedia.setCreatedAt(LocalDateTime.now());
                postMedia.setType("img");
                Integer insert = postMapper.insertPostMedia(postMedia);
            }
            return true;
        }
        return false;
    }

    @Override
    public Boolean updatePost(Post post) {
        post.setUpdatedAt(LocalDateTime.now());
        post.setAudi(0);
        post.setUpdatedAt(LocalDateTime.now());
        post.setAudi(0);
        Integer isinsertPost = postMapper.updatePost(post);
        if (isinsertPost != 1) {
            return false;
        }
        // 从数据库中获取现有的`post_media`记录
        List<String> existingUrls = postMapper.getMediaByPostId(post.getPostId());
        List<String> newUrls = post.getUrl();

        // 找出需要插入和删除的URL
        Set<String> existingSet = new HashSet<>(existingUrls);
        Set<String> newSet = new HashSet<>(newUrls);

        // 需要插入的URL
        Set<String> urlsToInsert = new HashSet<>(newSet);
        urlsToInsert.removeAll(existingSet);

        // 需要删除的URL
        Set<String> urlsToDelete = new HashSet<>(existingSet);
        urlsToDelete.removeAll(newSet);

        // 执行删除操作
        if (!urlsToDelete.isEmpty()) {
            for(String url : urlsToDelete){
                postMapper.deletePostMedia(url);
            }

        }
        // 执行插入操作
        for (String url : urlsToInsert) {
            PostMedia postMedia = new PostMedia();
            postMedia.setUrl(url);
            postMedia.setPostId(post.getPostId());
            postMedia.setCreatedAt(LocalDateTime.now());
            postMedia.setType("img");
            postMapper.insertPostMedia(postMedia);
        }

        return true;
    }

    @Override
    public Pagebean getPosts(Integer userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        LocalDateTime dateTime = LocalDateTime.now();
        List<Post> list = postMapper.getPosts(dateTime);
        PageInfo<Post> p = new PageInfo<>(list);
        Pagebean pagebean = new Pagebean(p.getTotal(), p.getList());
        System.out.println(p.getTotal() + "  " + p.getList().size());
        for (Post post : list) {
            List<String> urls = postMapper.getMediaByPostId(post.getPostId());
            post.setUrl(urls);
        }
        return pagebean;
    }
    public List<Post> getAllPost() {
        List<Post> list = postMapper.getHotPosts();
        PageInfo<Post> p = new PageInfo<>(list);
        Pagebean pagebean = new Pagebean(p.getTotal(), p.getList());
        System.out.println(p.getTotal() + "  " + p.getList().size());
        for (Post post : list) {
            List<String> urls = postMapper.getMediaByPostId(post.getPostId());
            post.setUrl(urls);
        }
        return list;
    }

    @Override
    public Post getPost(Integer postId) {
      Post post = postMapper.getPost(postId);
        List<String> urls = postMapper.getMediaByPostId(post.getPostId());
        post.setUrl(urls);
        return post;
    }

    public List<Post> getWorks(Integer userId,Integer audi){
        List<Post> list;
        if(audi==2){
             list =  postMapper.getWorks(userId);
        }
        else{
            list =  postMapper.getWorksByAudi(userId,audi);
        }

        for (Post post : list) {
            List<String> urls = postMapper.getMediaByPostId(post.getPostId());
            post.setUrl(urls);
        }
        return list;
    }

    public  List<Integer> getWorksId(Integer userId){
        return postMapper.getWorksId(userId);
    }
    public boolean ispostLiked(Integer postId, Integer userId) {
        return likesLogService.isLiked(userId,postId,2);
    }

    @Override
    public Post getNewPost(Integer userId) {
        Post post = postMapper.getNewPost(userId);
        List<String> urls = postMapper.getMediaByPostId(post.getPostId());
        post.setUrl(urls);
        return post;
    }

    @Override
    public Pagebean searchPosts(Post post, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        LocalDateTime dateTime = LocalDateTime.now();
        List<Post> list = postMapper.searchPosts(post);
        PageInfo<Post> p = new PageInfo<>(list);
        Pagebean pagebean = new Pagebean(p.getTotal(), p.getList());
        System.out.println(p.getTotal() + "  " + p.getList().size());
        for (Post post1 : list) {
            List<String> urls = postMapper.getMediaByPostId(post1.getPostId());
            post1.setUrl(urls);
        }
        return pagebean;
    }

    @Override
    public Integer audi(Integer postId, Integer audi) {
        return postMapper.audi(postId,audi);
    }

    public Integer postLike(Integer postId, Integer userId) {
        if(likesLogService.isLiked(userId,postId,2)){
            return 0;
        }
            return likesLogService.like(userId,postId,2);

    }
    public Integer deletePost(Integer postId){
        return postMapper.deletePost(postId);
    }
}
