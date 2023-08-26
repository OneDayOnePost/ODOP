package com.example.restcontroller.GR;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Dope;
import com.example.entity.Post;
import com.example.repository.GR.GrDopeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/api/blog")
@RequiredArgsConstructor
public class GrRestdopeController {

    final GrDopeRepository dRepository;

    @PostMapping(value = "/dope.json")
    public Map<String, Integer> dopePOST(@RequestBody Dope dope){

        Map<String, Integer> retMap = new HashMap<>();

        try {
            int ret = dRepository.countByEmailAndPost_no(dope.getEmail(), dope.getPost().getNo());
            // int ret = dRepository.countByEmailAndPost_no(map.get("id").toString(), BigDecimal.valueOf((Long.parseLong(map.get("postno").toString()))));
            
            if (ret == 1 ){
                dRepository.deleteByEmailAndPost_no(dope.getEmail(), dope.getPost().getNo());

                retMap.put("result", 1);
                retMap.put("dopestate", 1);
            }
            else {
            
                dope.setEmail(dope.getEmail());

                Post post = new Post();
                post.setNo(dope.getPost().getNo());
                dope.setPost(post);

                dRepository.save(dope);
                retMap.put("result", 1);
                retMap.put("dopestate", 0);
            }


        } catch (Exception e) {
            retMap.put("result", 0);
        }

        return retMap;
    }

    @PostMapping(value = "/dopeCounts.json")
    public List<Integer> getDopeCounts(@RequestBody List<Long> postNos){
        
    
        List<Integer> dopeCounts = new ArrayList<>();
        for( Long postNo : postNos){
            int count = dRepository.countByPost_no(BigInteger.valueOf(postNo));
            dopeCounts.add(count);
        }
        return dopeCounts;

    }
    
}
