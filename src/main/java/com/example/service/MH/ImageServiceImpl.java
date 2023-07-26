package com.example.service.MH;

import org.springframework.stereotype.Service;

import com.example.entity.Image;
import com.example.repository.MH.MhImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    final private MhImageRepository mhImageRepository;

    @Override
    public int insertPostImageOne(Image obj) {
        
        try {
            mhImageRepository.save(obj);
            return 1;
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
}
