package org.example.tlias.mapper;

import org.apache.ibatis.annotations.*;
import org.example.tlias.pojo.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("insert into comments ( post_id, user_id, content, created_at) value " +
            "(#{postId},#{userId},#{content},#{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "commentId")
    Integer insertComment(Comment comment);
    @Select("  SELECT *  FROM comments  WHERE post_id =#{post_id}  ORDER BY likes_count DESC, created_at ASC")
    List<Comment> getCommnetsByPostId(Integer postId);

  /*  @Update("update  comments set likes_count = likes_count+1 where comment_id = #{commnetId}")
    Integer commentLike(Integer commentId);

*/
    @Select("select * from comments where post_id = #{postId} ORDER BY created_at DESC")
    List<Comment> getMsg(Integer postId);
}
