package com.example.mapper.GR;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import com.example.dto.CateDTO;
import com.example.dto.MemberDTO;
import com.example.dto.PostDTO;
import com.example.entity.Post;
import com.example.entity.GR.postall;

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
            " SELECT category AS category, count(cateno) AS count FROM (SELECT c.CATEGORY, p.* FROM cate c LEFT JOIN POST p ON c.NO = p.CATENO AND p.writer = #{email}) GROUP BY category " })
    public List<Map<String, Integer>> selectpostcatecount(String email);

    // post 갯수 세기
    @Select({ " SELECT COUNT(*) FROM POST WHERE WRITER = #{email} " })
    public int countpostall(String email);

    // post전체 조회
    // @Select({ " SELECT * FROM POST WHERE WRITER = #{email} " })
    // public List<PostDTO> selectpostAll(String email);
    @Select({ "SELECT NO, WRITER, TITLE, CONTENT, HIT, REGDATE, CATENO, SECRET, TAGNO, POSTNO, TAG FROM ",
            " ( SELECT *, ROW_NUMBER() OVER (PARTITION BY NO ORDER BY REGDATE DESC) AS rn FROM postall WHERE WRITER = #{email}) AS sub",
            " WHERE rn = 1" })
    public List<postall> selectpostAll(String email);

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
