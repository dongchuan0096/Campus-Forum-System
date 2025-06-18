package org.example.tlias.controller;

import org.example.tlias.pojo.Post;
import org.example.tlias.pojo.User;
import org.example.tlias.service.PostService;
import org.example.tlias.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin
@RestController
public class PostController {
    @Autowired
    PostService postService;
    @PreAuthorize("hasAuthority('CAN_POST')")
    @PostMapping("/user/post")
    public Result insertPost(@RequestBody Post post) {
        System.out.println("//////////////////////////" + post);
        if (postService.insertPost(post)) {
            return new Result(40, "添加成功", null);
        } else {
            return new Result(0, "添加失败", null);
        }
    }
    @PostMapping("/user/post/update")
    public Result updatePost(@RequestBody Post post) {
        System.out.println("//////////////////////////" + post);
        if (postService.updatePost(post)) {
            return new Result(40, "更新成功", null);
        } else {
            return new Result(0, "更新失败", null);
        }
    }
    @PostMapping("/admin/delete")
    public Result delete(@RequestParam Integer postId){
        return new Result(1,"成功",postService.deletePost(postId));
    }
    @GetMapping("/user/getpost")
    public Result getPostByPostId(@RequestParam Integer postId) {
        return Result.success(postService.getPost(postId));
    }
    @GetMapping("/admin/getpost")
    public Result getPostByPostId1(@RequestParam Integer postId) {
        return Result.success(postService.getPost(postId));
    }

    @GetMapping("/user/getposts")
    public Result getPosts(@RequestParam Integer userId, @RequestParam Integer page, @RequestParam Integer pageSize) {
        return new Result(1, "成功", postService.getPosts(userId, page, pageSize));
    }

    @GetMapping("/user/getallpost")
    public Result getAllPosts() {
        return new Result(1, "成功", postService.getAllPost());
    }

    @GetMapping("/user/works")
    public Result getWorks(@RequestParam Integer userId,Integer audi) {
        return new Result(1, "用户作品", postService.getWorks(userId ,audi));
    }

    @PostMapping("/user/postlike")
    public Result postlike(@RequestParam Integer postId, @RequestParam Integer userId) {
        if (postService.postLike(postId, userId) == 1) {
            return Result.success();
        } else
            return Result.error("点赞失败");
    }

    @PostMapping("/user/ispostliked")
    Result iscommentLiked(@RequestParam Integer postId, @RequestParam Integer userId) {
        if (postService.ispostLiked(postId, userId)) {
            return new Result(60, "点赞过", null);
        } else
            return new Result(61, "未点赞", null);
    }
    @GetMapping("/user/getnewpost")
    Result getNewPost(@RequestParam Integer userId){
        return  Result.success(postService.getNewPost(userId));
    }
    @PostMapping("/admin/searchposts")
    Result searchPosts(Integer postId, Integer userId, String title, String content,LocalDateTime createdAt, LocalDateTime updatedAt,Integer audi, @RequestParam Integer page, @RequestParam Integer pageSize){
        Post post = new Post();
        post.setPostId(postId);
        post.setUserId(userId);
        post.setContent(content);
        post.setTitle(title);
        post.setCreatedAt(createdAt);
        post.setUpdatedAt(updatedAt);
        post.setAudi(audi);
        return Result.success(postService.searchPosts(post,page,pageSize));
    }
    @PostMapping("/user/searchposts")
    Result searchPosts1(Integer postId, Integer userId, String title, String content,LocalDateTime createdAt, LocalDateTime updatedAt,Integer audi, @RequestParam Integer page, @RequestParam Integer pageSize){
        Post post = new Post();
        post.setPostId(postId);
        post.setUserId(userId);
        post.setContent(content);
        post.setTitle(title);
        post.setCreatedAt(createdAt);
        post.setUpdatedAt(updatedAt);
        post.setAudi(audi);
        return Result.success(postService.searchPosts(post,page,pageSize));
    }
    @PostMapping("/admin/audi")
    Result audi(@RequestParam Integer postId ,@RequestParam Integer audi){
        return Result.success(postService.audi(postId,audi));
    }
}