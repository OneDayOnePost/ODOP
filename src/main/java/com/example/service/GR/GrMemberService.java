package com.example.service.GR;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.dto.MemberDTO;
import com.example.entity.Post;

@Service
public interface GrMemberService {

    // user 정보 가져오기
    public MemberDTO selectMemberOne(String email);

    // user 팔로잉 카운트 
    public int countfollowing(String email);

    // user 팔로워 카운트
    public int countfollower(String email);

    // category 게시글 카운트하기
    public List<Map<String, Integer>> selectpostcatecount(String email);

    // post태그 조회
    public List<Map<String, Integer>> selectposttag(String email, BigInteger postno);

    // post댓글 조회
    public int countreply(BigInteger postno);

    // tag 불러오기
    public List<Map<BigInteger, String>> selecttag(BigInteger postno);

    // 작성자로 post조회
    List<Post> findByWriterOrderByNoDesc(String writer);

    // 태그로 post조회
    List<Post> findByPostTagListTag(String tag);

    // 카테고리 별 조회
    List<Post> findByWriterAndCateNoOrderByNoDesc(String writer, BigInteger cateno);


}
