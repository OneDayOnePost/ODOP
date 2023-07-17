package com.example.service.MH;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Cate;
import com.example.entity.Post;
import com.example.repository.MH.CateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostInsertServiceimpl implements PostInsertService {

    final private CateRepository cateRepository;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertPostOne'");
    }
    
}
