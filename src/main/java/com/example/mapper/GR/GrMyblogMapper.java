package com.example.mapper.GR;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.dto.MemberDTO;

@Mapper
public interface GrMyblogMapper {

    // user 정보 가져오기 (test용)
    @Select({ " SELECT * FROM member WHERE email=#{email} " })
    public MemberDTO selectMemberOne(String email);

    // user 팔로잉 카운트 (test용)
    @Select({ " SELECT COUNT(*) FROM FOLLOW WHERE MYID=#{email} " })
    public int countfollowing(String email);

    // user 팔로워 카운트 (test용)
    @Select({ " SELECT COUNT(*) FROM FOLLOW WHERE YOURID=#{email} " })
    public int countfollower(String email);

    // category 게시글 카운트하기
    @Select({
            " SELECT c.NO, c.category, count(p.no) AS pnocount FROM cate c LEFT JOIN post p ON c.NO = p.CATENO AND p.WRITER = #{email} GROUP BY c.category ORDER BY NO ASC; " })
    public List<Map<String, Integer>> selectpostcatecount(String email);

    // post태그 조회
    @Select({ "SELECT * FROM postall WHERE WRITER=#{email} AND NO=#{postno}" })
    public List<Map<String, Integer>> selectposttag(String email, BigInteger postno);

    // post댓글 조회
    @Select({ " SELECT COUNT(*) FROM REPLY  WHERE POSTNO = #{postno} " })
    public int countreply(BigInteger postno);

    // tag 불러오기
    @Select({ "SELECT postno, tag FROM POST_TAG WHERE POSTNO = #{postno} " })
    public List<Map<BigInteger, String>> selecttag(BigInteger postno);
}
