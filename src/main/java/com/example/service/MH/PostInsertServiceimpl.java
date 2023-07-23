package com.example.service.MH;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Cate;
import com.example.entity.Post;
import com.example.entity.PostTag;
import com.example.repository.MH.CateRepository;
import com.example.repository.MH.PostRepository;
import com.example.repository.MH.PostTagRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostInsertServiceimpl implements PostInsertService {

    final String format = "PostInsertServiceImpl => {}";

    final private CateRepository cateRepository;
    final private PostRepository postRepository;
    final private PostTagRepository postTagRepository;

    @Override
    public List<Cate> selectCateList() {
        
        try {
            return cateRepository.findAll();
        }

        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertPostOne(Post obj) {
        try {

            // 1. 글 등록
            postRepository.save(obj);

            // 2. 해시태그 등록
            Post post = postRepository.findRecentPostByWriter("test1@gmail.com");

            // log.info(format, post.toString());

            List<PostTag> taglist = new ArrayList<>();

            for(String tag : obj.getTagList()) {

                PostTag postTag = new PostTag();
                
                postTag.setPost(post);
                postTag.setTag(tag);

                taglist.add(postTag);
            }

            // log.info(format, taglist.toString());

            postTagRepository.saveAll(taglist);

            // 3. 팔로워들에게 알림 전송

            return 1;
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
}
