package com.example.service.MH;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Reply;

@Service
public interface ReplyService {

    // 1. 댓글 등록
    public int insertReplyOne(Reply obj);

    // 2. 댓글 조회
    public List<Reply> selectReplyList(BigInteger postno);

    // 3. 댓글 삭제
    public int deleteReplyOne(Reply obj);

    // 4. 댓글 수정
    public int updateReplyOne(Reply obj);
    
}
