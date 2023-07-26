package com.example.service.MH;

import org.springframework.stereotype.Service;

import com.example.entity.Image;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public int insertPostImageOne(Image obj) {
        
        try {
            
            return 1;
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
}
