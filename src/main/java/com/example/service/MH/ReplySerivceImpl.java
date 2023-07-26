package com.example.service.MH;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Reply;
import com.example.repository.MH.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplySerivceImpl implements ReplyService {

    final private ReplyRepository replyRepository;

    @Override
    public int insertReplyOne(Reply obj) {

        try {
            replyRepository.save(obj);
            return 1;
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Reply> selectReplyList(BigInteger postno) {

        try {
            return replyRepository.findByPost_noOrderByRepgroupDesc(postno);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteReplyOne(Reply obj) {

        try {
            replyRepository.deleteById(obj.getNo());
            return 1;
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int updateReplyOne(Reply obj) {

        try {

            Reply reply = replyRepository.findById(obj.getNo()).orElse(null);
            reply.setContent(obj.getContent());
            reply.setSecret(obj.getSecret());

            replyRepository.save(reply);

            return 1;
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
}
