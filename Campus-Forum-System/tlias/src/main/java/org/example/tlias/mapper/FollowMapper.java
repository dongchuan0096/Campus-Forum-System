package org.example.tlias.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.tlias.pojo.Follow;
import org.example.tlias.service.FollowService;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface FollowMapper {
    @Insert("insert into follows (follow_id, user_id, be_followed_id, follow_at_time) VALUE (#{followId},#{userId},#{beFollowedId},#{followAtTime})")
    Integer insertFollow(Follow follow);

    @Select("select * from follows where user_id = #{userId} and be_followed_id = #{beFollowedId}")
    Follow isFollowed(Integer userId,Integer beFollowedId);
    /*获取userId的关注列表*/
    @Select("select * from follows where user_id = #{userId}")
    List<Follow> getFollowsByuserId(Integer userId);
    @Select("select * from follows where be_followed_id = #{beFollowedId} order by follow_at_time desc")
    List<Follow> getFollowers(Integer beFollowedId);
    /*befollowedId的粉丝数*/
    @Select("select count(user_id) from  follows where be_followed_id = #{beFollowedId} and follow_at_time > #{dataTime}")
    Integer getFollowersCountBybeFollowedId(Integer beFollowedId, LocalDateTime dataTime);
    /*userId的关注数*/
    @Select("select count(be_followed_id) from  follows where user_id = #{userId} ")
    Integer getFollowersCountByuserId(Integer userId);
}
