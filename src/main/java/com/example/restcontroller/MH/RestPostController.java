package com.example.restcontroller.MH;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Multipart;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Image;
import com.example.entity.Post;
import com.example.service.MH.ImageService;
import com.example.service.MH.PostInsertService;
import com.example.service.MH.PostManageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/post")
@RequiredArgsConstructor
@Slf4j
public class RestPostController {
    
    final String format = "RestPostController => {}";
    
    final private PostInsertService pService;
    final private PostManageService postManageService;
    final private ImageService imageService;

    @PostMapping(value = "/write.json")
    public Map<String, Object> writePOST(@RequestBody Post obj) {

        Map<String, Object> retMap = new HashMap<>();

        // log.info(format, obj.toString());

        obj.setWriter("test1@gmail.com"); // 로그인 상태에서 받아올 작성자 이메일 정보

        int ret = pService.insertPostOne(obj);
        
        if(ret == 1) {
            retMap.put("status", 200);
        }
        else {
            retMap.put("status", -1);
        }

        return retMap;
    }

    @PutMapping(value = "/update.json")
    public Map<String, Object> updatePUT(@RequestBody Post obj) {

        Map<String, Object> retMap = new HashMap<>();

        retMap.put("status", -1);

        log.info(format, obj.toString());

        int ret = postManageService.updatePostOne(obj);

        if(ret == 1) {
            retMap.put("status", 200);
        }

        return retMap;
    }

    @PostMapping(value = "/image/upload.json")
    public Map<String, Object> uploadImagePOST(@RequestParam("image") MultipartFile multipartFile) throws IOException {

        Map<String, Object> retMap = new HashMap<>();

        // log.info(format, multipartFile.getOriginalFilename());

        Image image = new Image();

        image.setFiledata(multipartFile.getBytes());
        image.setFilename(multipartFile.getOriginalFilename());
        image.setFilesize(BigInteger.valueOf(multipartFile.getSize()));
        image.setFiletype(multipartFile.getContentType());

        int ret = imageService.insertPostImageOne(image);

        retMap.put("status", -1);
        
        if(ret == 1) {
            retMap.put("status", 200);
        }

        return retMap;

    }

}
