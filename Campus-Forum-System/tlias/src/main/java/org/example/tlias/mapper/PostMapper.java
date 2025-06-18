package org.example.tlias.mapper;

import org.apache.ibatis.annotations.*;
import org.example.tlias.pojo.Emp;
import org.example.tlias.pojo.Post;
import org.example.tlias.pojo.PostMedia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface PostMapper {
    /*插入帖子*/
    @Insert("insert into posts (user_id, title, content,created_at,updated_at,publish_at,audi) values (#{userId},#{title},#{content},#{createdAt},#{updatedAt},#{publishAt},#{audi})")
    @Options(useGeneratedKeys = true, keyProperty = "postId")
    Integer insertPost(Post post);
    @Update("update posts set title = #{title} ,content = #{content} , updated_at = #{updatedAt} , audi = #{audi} where post_id = #{postId}")
    Integer updatePost(Post post);
    /*插入帖子图片*/
    @Insert("insert into post_media(media_id,post_id, url, type, created_at) value (#{mediaId},#{postId},#{url},#{type},#{createdAt})")
    Integer insertPostMedia(PostMedia postMedia);
    /*查询帖子*/
    List<Post> getPosts(LocalDateTime now);
    @Select("select url from post_media where post_id = #{postId}")
    List<String> getMediaByPostId(Integer postId);
    @Delete( "delete  from post_media where url = #{url} ")
    Integer deletePostMedia(String url);
    @Select("select * from posts where user_id = #{userId}")
    List<Post> getWorks(Integer userId);
    @Select("select * from posts where user_id = #{userId} and audi = #{audi}")
    List<Post> getWorksByAudi(Integer userId,Integer audi);
    @Select("select * from posts where post_id = #{postId}")
    Post getPost(Integer postId);
    @Delete("delete from posts where post_id = #{postId}")
    Integer deletePost(Integer postId);

    @Select("select post_id from posts where user_id = #{userId}")
    List<Integer> getWorksId(Integer userId);
/*    @Update("update  posts set likes_count = likes_count+1 where post_id = #{post_id}")
    Integer postLike(Integer postId);*/
    @Update("update  posts set comments_count=comments_count+1 where post_id = #{postId}")
    Integer commentsCount(Integer postId);
    @Select("select * from posts where user_id = #{userId} order by created_at desc limit 1 ")
    Post getNewPost(Integer userId);

    List<Post> searchPosts(Post post);
    @Update("update posts set audi = #{audi}  where post_id = #{postId}")
    Integer audi(Integer postId,Integer audi);

    @Select("SELECT *\n" +
            "FROM posts\n" +
            "ORDER BY likes_count DESC\n" +
            "LIMIT 10;\n")
    List<Post> getHotPosts();

    HashMap getPostDetails();
}
