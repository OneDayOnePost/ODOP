package com.example.service.MH;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

import com.example.entity.Post;
import com.example.repository.MH.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostSelectServiceImpl implements PostSelectService {

    final private PostRepository postRepository;

    @Override
    public Post selectPostOne(BigInteger no) {
        
        try {
            return postRepository.findById(no).orElse(null);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
