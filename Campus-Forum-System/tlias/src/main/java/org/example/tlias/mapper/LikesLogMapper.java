package org.example.tlias.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.tlias.pojo.LikesLog;

import java.time.LocalDateTime;

@Mapper
public interface LikesLogMapper {


   @Insert("insert into likes_log (user_id,created_at,liked_at,liked_at_id) value (#{userId},#{createdAt},#{likedAt},#{likedAtId})")
   Integer insertLike(LikesLog likeslog);
   /*
   * @Param Integer Id, Integer userId,Integer at 点赞类型的id，点赞人的userId，点赞类型（1：评论，2帖子）
   * @Return Likeslog 表记录
   *
   * */
   @Select("select * from likes_log where liked_at = #{at} and liked_at_id = #{Id} and user_id = #{userId}")
   LikesLog isLiked(Integer userId, Integer Id,Integer at);
   @Select("select count(*) from likes_log where liked_at = #{likedAt} and liked_at_id = #{likedAtId} and created_at > #{createdAt}")
   Integer getLikes (Integer likedAtId,Integer likedAt, LocalDateTime createdAt);
}
