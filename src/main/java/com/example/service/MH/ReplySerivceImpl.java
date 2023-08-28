package com.example.service.MH;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
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

            

            sendAlertMessage(obj);

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* 알림 전송 */
    private int sendAlertMessage(Reply obj) {

        // 1. 댓글 작성자 확인 - 본인이 게시물이 아닐 때만

        // 댓글 작성자
        String user = obj.getWriter();

        Post post = postRepository.findById(obj.getPost().getNo()).orElse(null);
        
        // 게시글 작성자
        String writer = post.getWriter();

        if (!user.equals(writer)) {

            // 2. 알림 내용 생성
            String title = (post.getTitle().length() > 7) ? post.getTitle().substring(0, 7) + "..."
                    : post.getTitle();
            String content = "[" + title + "]에 댓글이 달렸습니다.";
            String url = "/blog/" + writer + "/select.do?postno=" + post.getNo();

            // 3. 댓글 알림이 존재하는지 확인


            
            int result = alertMapper.alertInsert(writer, content, "댓글", url, obj.getRegdate());

            System.out.println(result);

            if (result < 1) {
                return -1;
            }

        }

        return 1;
    }

    @Override
    public List<Reply> selectReplyList(BigInteger postno, BigInteger repdepth, BigInteger repgroup) {

        try {

            // 정렬에 사용할 sort 변수
            Sort sort = null;

            // return 할 List 객체 초기화
            List<Reply> list = new ArrayList<>();

            /* 1. 댓글 or 답글 확인 ( repdepth 값 확인 ) */
            // 댓글일 경우 ( repdepth == 0 )
            if (repdepth.compareTo(new BigInteger("0")) == 0) {

                sort = Sort.by("no").descending(); // 최신순
                list = replyRepository.findByPost_noAndRepdepthAndState(postno, repdepth, new BigInteger("0"), sort);

            }
            // 답글일 경우 ( repdepth == 1 )
            else if (repdepth.compareTo(new BigInteger("1")) == 0) {

                sort = Sort.by("no").ascending(); // 오래된순
                list = replyRepository.findByPost_noAndRepdepthAndStateAndRepgroup(postno, repdepth,
                        new BigInteger("0"), repgroup, sort);

            }

            /* 2. 댓글 or 답글 조회 */

            return list;

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
