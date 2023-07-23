package com.example.service.MH;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Post;
import com.example.entity.PostTag;
import com.example.repository.MH.PostRepository;
import com.example.repository.MH.PostTagRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostManageServiceImpl implements PostManageService {

    final String format = "PostManageServiceImpl => {}";
    final private PostRepository postRepository;
    final private PostTagRepository postTagRepository;

    @Override
    public int updatePostOne(Post obj) {

        try {
            
            // 1. 글 번호로 기존 데이터 조회

            Post post = postRepository.findById(obj.getNo()).orElse(null);

            // 2. 데이터 수정
    
            post.setCate(obj.getCate());
            post.setContent(obj.getContent());
            post.setMarkdown(obj.getMarkdown());
            post.setTitle(obj.getTitle());
            post.setSecret(obj.getSecret());
            
            // 3. 기존 해시태그 전체 삭제

            postTagRepository.deleteByPost_no(obj.getNo());

            // 4. 변경된 해시태그 목록 등록

            List<PostTag> taglist = new ArrayList<>();

            for(String tag : obj.getTagList()) {

                PostTag postTag = new PostTag();
                
                postTag.setPost(post);
                postTag.setTag(tag);

                taglist.add(postTag);
            }

            postTagRepository.saveAll(taglist);

            return 1;
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }

    }
    

}
