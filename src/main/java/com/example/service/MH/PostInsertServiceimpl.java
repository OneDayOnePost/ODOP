package com.example.service.MH;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Cate;
import com.example.entity.Image;
import com.example.entity.Post;
import com.example.entity.PostImage;
import com.example.entity.PostTag;
import com.example.repository.MH.CateRepository;
import com.example.repository.MH.MhImageRepository;
import com.example.repository.MH.PostImageRepository;
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
    final private MhImageRepository imageRepository;
    final private PostImageRepository postImageRepository;

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
            Post post = postRepository.findRecentPostByWriter(obj.getWriter());

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

            // 3. 이미지 키 & 경로 등록

            imageRepository.saveAll(obj.getImageList());

            // 4. 등록한 이미지 데이터 PK 조회

            List<Image> imageList = imageRepository.findRecentImageListByEmail(obj.getWriter(), obj.getImageList().size());
            
            // 5. 게시글-이미지 테이블 등록

            List<PostImage> postImages = new ArrayList<>();

            for(int i=0; i<imageList.size(); i++) {

                PostImage postImage = new PostImage();

                Image image = imageList.get(i);

                // 대표이미지 설정

                if(i == imageList.size()-1) {
                    image.setChk(new BigInteger("1"));
                }
                
                postImage.setImage(image);
                postImage.setPost(post);

                postImages.add(postImage);

            }

            postImageRepository.saveAll(postImages);
            
            // 6. 팔로워들에게 알림 전송

            return 1;
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
}
