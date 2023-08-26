package com.example.service.MH;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Post;
import com.example.entity.PostTagProjection;
import com.example.repository.MH.PostRepository;
import com.example.repository.MH.PostTagRepository;
import com.example.repository.MH.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostSelectServiceImpl implements PostSelectService {

    final private PostRepository postRepository;
    final private PostTagRepository postTagRepository;
    final private ReplyRepository replyRepository;

    @Override
    public Post selectPostOne(BigInteger no) {
        
        try {

            // 1. 글 번호로 글 조회

            Post post = postRepository.findById(no).orElse(null);

            // 2. 글 번호로 댓글수 조회

            int replyCount = replyRepository.countByPost_noAndRepdepthAndState(no, new BigInteger("0"), new BigInteger("0"));

            // 3. 댓글수를 Post의 댓글수 저장 임시 변수에 set

            post.setReplyCount(replyCount);

            return post; 
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PostTagProjection> selectPostTagList(BigInteger no) {
        
        
        try {
            return postTagRepository.findByPost_no(no);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
