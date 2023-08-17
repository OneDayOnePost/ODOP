package com.example.mapper.WJ;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.entity.Alert;

@Mapper
public interface WjAlertMapper {
    // 팔로우 알림
    @Insert({" INSERT INTO ALERT (email, content, type, url) VALUES (#{email}, #{content}, '팔로우', #{url}) "})
    public int followInsert(@Param("email") String email, @Param("content") String content, @Param("url") String url);

    // 알림 조회
    @Select({" SELECT * FROM ALERT WHERE email = #{email} ORDER BY no DESC "})
    public List<Alert> selectAlertList(@Param("email") String email);
}
