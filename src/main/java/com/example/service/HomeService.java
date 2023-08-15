package com.example.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.dto.PostAllViewDTO;

@Service
public interface HomeService {
    
    public List<PostAllViewDTO> selectPostList(Map<String, Object> params);

}
