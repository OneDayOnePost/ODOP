package com.example.mapper.WJ;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.dto.AlertDTO;
import com.example.entity.Alert;

@Mapper
public interface WjAlertMapper {
    // 알림 추가
    @Insert({" INSERT INTO ALERT (email, content, type, url, regdate) VALUES (#{email}, #{content}, #{type}, #{url}, #{regdate}) "})
    public int alertInsert(@Param("email") String email, @Param("content") String content, @Param("type") String type, @Param("url") String url, @Param("regdate") Date regdate);

    // 알림 추가 ( DTO 타입으로 파라미터 전달 )
    @Insert({
        " INSERT INTO ALERT (email, content, type, url) ",
        " VALUES (#{alert.email}, #{alert.content}, #{alert.type}, #{alert.url})"
    })
    public int alertInsert2(@Param("alert") AlertDTO alert);

    // 알림 조회 (한 달 이내)
    @Select({" SELECT * FROM ALERT WHERE email = #{email} AND regdate >= DATEADD('MONTH', -1, CURRENT_TIMESTAMP) ORDER BY no DESC "})
    public List<Alert> selectAlertList(@Param("email") String email);

    // 알림 상태 업데이트 (chk=0 -> chk=1)
    @Update({" UPDATE alert SET chk=1 WHERE no = #{no} "})
    public int updateAlertChk(@Param("no") BigInteger no);

    // 읽지 않은(chk=0) 알림 개수
    @Select({" SELECT COUNT(NO) FROM ALERT WHERE email = #{email} AND regdate >= DATEADD('MONTH', -1, CURRENT_TIMESTAMP) AND chk=0 "})
    public BigInteger selectAlertCount(@Param("email") String email);

    // 팔로워 목록
    @Select({" SELECT myid FROM follow WHERE yourid = #{email} "})
    public List<String> selectFollowerList(@Param("email") String email);

    // 댓글 알림 존재 확인 ( 있으면 update 실행, 없으면 insert 실행 )
    @Select({
        " SELECT COUNT(*) FROM alert WHERE type = #{alert.type} AND url = #{alert.url} "
    })
    public int selectReplyAlertCount(@Param("alert") AlertDTO alert);

    // 댓글 알림 최신화
    @Update({
        " UPDATE alert SET content = #{alert.content}, chk = 0 WHERE type = #{alert.type} AND url = #{alert.url} "
    })
    public int updateReplyAlert(@Param("alert") AlertDTO alert);
}
