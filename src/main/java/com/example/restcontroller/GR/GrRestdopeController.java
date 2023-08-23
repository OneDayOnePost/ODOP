package com.example.restcontroller.GR;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
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
    public Map<String, Integer> dopePOST(@RequestBody Map<String, Object> map){

        Map<String, Integer> retMap = new HashMap<>();

        try {

            Dope dope = new Dope();

            int ret = dRepository.countByEmailAndPost_no(map.get("id").toString(), BigDecimal.valueOf((Long.parseLong(map.get("postno").toString()))));
            
            if (ret == 1 ){
                dRepository.deleteByEmailAndPost_no(map.get("id").toString(), BigDecimal.valueOf((Long.parseLong(map.get("postno").toString()))));

                retMap.put("result", 1);
                retMap.put("dopestate", 1);
            }
            else {
            
                dope.setEmail(map.get("id").toString());

                Post post = new Post();
                post.setNo(BigInteger.valueOf(Long.parseLong(map.get("postno").toString())));
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
    
}
