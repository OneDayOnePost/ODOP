package com.example.restcontroller.GR;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Member;
import com.example.repository.GR.GrMemberRepository;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class GrRestCategoryController {

    final private GrMemberRepository gRepository;
    
    @PutMapping(value="/mypage.json")
    public Map<String, Object> mypagePUT(@RequestBody Member obj)  {
        
        Map<String, Object> retMap = new HashMap<>();

        try {
            Member member = gRepository.findById(obj.getEmail()).orElse(null);

            member.setImgkey(obj.getImgkey());
            member.setImgpath(obj.getImgpath());

            gRepository.save(member);
            
            retMap.put("status", 200);
        } catch (Exception e) {
            e.printStackTrace();

            retMap.put("status", -1);
        }

        return retMap;
    }
    
}
