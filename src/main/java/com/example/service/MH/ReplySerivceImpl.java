package com.example.service.MH;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Post;
import com.example.entity.Reply;
import com.example.mapper.WJ.WjAlertMapper;
import com.example.repository.MH.PostRepository;
import com.example.repository.MH.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplySerivceImpl implements ReplyService {

    final private ReplyRepository replyRepository;
    final private PostRepository postRepository;
    final private WjAlertMapper alertMapper;

    @Override
    public int insertReplyOne(Reply obj) {

        try {
            replyRepository.save(obj);

            /* 댓글 알림 전송 */

            // 1. 댓글 작성자 확인 - 본인이 게시물이 아닐 때만

            String user = obj.getWriter();

            Post post = postRepository.findById(obj.getPost().getNo()).orElse(null);

            String writer = post.getWriter();

            if (!user.equals(writer)) {
                String title = (post.getTitle().length() > 7) ? post.getTitle().substring(0, 7) + "..."
                        : post.getTitle();
                String content = "[" + title + "]에 댓글이 달렸습니다.";
                String url = "/blog/" + writer + "/select.do?postno=" + post.getNo();
                int result = alertMapper.alertInsert(writer, content, "댓글", url, obj.getRegdate());

                System.out.println(result);

                if (result < 1) {
                    return -1;
                }

            }

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Reply> selectReplyList(BigInteger postno) {

        try {
            return replyRepository.findByPost_noOrderByRepgroupDesc(postno);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteReplyOne(Reply obj) {

        try {
            replyRepository.deleteById(obj.getNo());
            return 1;
        } catch (Exception e) {
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
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
