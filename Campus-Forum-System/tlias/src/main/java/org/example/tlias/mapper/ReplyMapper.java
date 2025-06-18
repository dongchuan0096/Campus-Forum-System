package org.example.tlias.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.tlias.pojo.Reply;

import java.util.List;

@Mapper
public interface ReplyMapper {
    @Insert("insert into replies (comment_id, user_id, content,reply_to_id,created_at,reply_to_username,post_id) VALUES (#{commentId},#{userId},#{content},#{replyToId},#{createdAt},#{replyToUsername},#{postId})")
    Integer insertReply(Reply reply);
    @Select("  SELECT *  FROM replies  WHERE comment_id =#{commnet_id}  ORDER BY  created_at ASC")
    List<Reply> getRepliesByCommentId(Integer commentId);

/*    @Update("update replies set likes_count = likes_count+1 where reply_id = #{replyId}")
    Integer replyLikes(Integer replyId);*/

    @Select("select * from replies where reply_to_id = #{userId} and user_id <> #{userId}")
    List<Reply> getReply(Integer userId);
}
