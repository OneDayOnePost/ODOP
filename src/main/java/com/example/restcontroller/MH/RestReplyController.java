package com.example.restcontroller.MH;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Reply;
import com.example.service.MH.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/reply")
public class RestReplyController {

    final String format = "RestReplyController => {}";

    final private ReplyService replyService;

    @PostMapping(value = "/write.json")
    public Map<String, Object> writePOST(@RequestBody Reply obj) {

        Map<String, Object> retMap = new HashMap<>();

        retMap.put("status", -1);

        log.info(format, obj.toString());

        obj.setWriter("test1@gmail.com");

        int ret = replyService.insertReplyOne(obj);

        if(ret == 1) {

            retMap.put("status", 200);

        }

        return retMap;

    }

    @GetMapping(value = "/select.json")
    public Map<String, Object> selectGET(@RequestParam(name = "postno") BigInteger postno) {

        Map<String, Object> retMap = new HashMap<>();

        // log.info(format, postno);

        List<Reply> list = replyService.selectReplyList(postno);

        // log.info(format, list);

        retMap.put("status", -1);

        if(!list.isEmpty()) {
            retMap.put("status", 200);
            retMap.put("list", list);
        }

        return retMap;

    }

    @PutMapping(value = "/delete.json")
    public Map<String, Object> deletePUT(@RequestBody Reply obj) {

        Map<String, Object> retMap = new HashMap<>();

        retMap.put("status", -1);

        int ret = replyService.deleteReplyOne(obj);

        if(ret == 1) {
            retMap.put("status", 200);
        }

        return retMap;

    }
    
}
