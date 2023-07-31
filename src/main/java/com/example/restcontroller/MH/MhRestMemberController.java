package com.example.restcontroller.MH;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Member;
import com.example.repository.MH.MhMemberRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/member")
@RequiredArgsConstructor
public class MhRestMemberController {

    final private MhMemberRepository memberRepository;

    @PutMapping(value = "/update.json")
    public Map<String, Object> updatePUT(@RequestBody Member obj) {

        Map<String, Object> retMap = new HashMap<>();

        try {

            Member member = memberRepository.findById(obj.getEmail()).orElse(null);

            member.setImgkey(obj.getImgkey());
            member.setImgpath(obj.getImgpath());

            memberRepository.save(member);
            
            retMap.put("status", 200);
        }

        catch (Exception e) {

            e.printStackTrace();

            retMap.put("status", -1);
        }

        return retMap;

    }

}
