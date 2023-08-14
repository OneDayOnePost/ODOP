package com.example.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/home")
public class RestHomeController {

    @GetMapping(value = "/getList.json")
    public Map<String, Object> getListGET(@RequestParam Map<String, Object> params) {
        
        Map<String, Object> retMap = new HashMap<>();

        log.info("getListGET => {}", 1);
        log.info("getListGET => {}", params.toString());    
    
        return retMap;
    }
    
}
