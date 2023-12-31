package com.example.restcontroller.GR;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api")
@Slf4j
@RequiredArgsConstructor
public class GrRestKakaoController {

    // 카카오 로그인
    @PostMapping(value = "/kakaologin.json")
    public Map<String, Integer> kakaologinPOST(@RequestBody Map<String, Object> map){
        Map<String, Integer> retMap = new HashMap<>();
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("ret", -1);
        }
        return retMap;
    }
    
}
