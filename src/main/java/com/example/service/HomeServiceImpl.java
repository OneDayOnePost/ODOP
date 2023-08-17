package com.example.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.dto.PostAllViewDTO;
import com.example.mapper.GR.GrHomeMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    final GrHomeMapper homeMapper;

    final String format = "HomeServiceImpl => {}";

    @Override
    public List<PostAllViewDTO> selectPostList(Map<String, Object> params) {
        try {

            List<PostAllViewDTO> list = homeMapper.selectPostList(params);

            for(PostAllViewDTO obj : list) {
                log.info(format, obj.getNo());
            }

            return list;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
