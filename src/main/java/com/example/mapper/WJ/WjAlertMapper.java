package com.example.mapper.WJ;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.Alert;

@Mapper
public interface WjAlertMapper {
    // 팔로우 알림
    @Insert({" INSERT INTO ALERT (email, content, type, url) VALUES (#{email}, #{content}, '팔로우', #{url}) "})
    public int followInsert(@Param("email") String email, @Param("content") String content, @Param("url") String url);

    // 알림 조회 (한 달 이내)
    @Select({" SELECT * FROM ALERT WHERE email = #{email} AND regdate >= DATEADD('MONTH', -1, CURRENT_TIMESTAMP) ORDER BY no DESC "})
    public List<Alert> selectAlertList(@Param("email") String email);

    // 알림 상태 업데이트 (chk=0 -> chk=1)
    @Update({" UPDATE alert SET chk=1 WHERE no = #{no} "})
    public int updateAlertChk(@Param("no") BigInteger no);

    // 읽지 않은(chk=0) 알림 개수
    @Select({" SELECT COUNT(NO) FROM ALERT WHERE email = #{email} AND regdate >= DATEADD('MONTH', -1, CURRENT_TIMESTAMP) AND chk=0 "})
    public BigInteger selectAlertCount(@Param("email") String email);
}
