package com.example.restcontroller.MH;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Image;
import com.example.entity.Post;
import com.example.service.MH.AwsS3Service;
import com.example.service.MH.ImageService;
import com.example.service.MH.PostInsertService;
import com.example.service.MH.PostManageService;
import com.example.service.WJ.WjAlertService;
import com.example.service.WJ.WjMemberService;

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
    final private AwsS3Service awsS3Service;

    final private WjAlertService WjaService;
    final private WjMemberService WjmService;

    @PostMapping(value = "/write.json")
    public Map<String, Object> writePOST(@RequestBody Post obj, @AuthenticationPrincipal User user) {

        Map<String, Object> retMap = new HashMap<>();

        // log.info(format, obj.toString());
        log.info(format, obj.getImageList());
        // log.info(format, obj.getContent());

        // 이미지 리스트와 본문 내용 비교 -> img src 경로 값이 없을 경우, 삭제!

        List<Image> imageList = obj.getImageList();

        for(Iterator<Image> iterator = imageList.iterator(); iterator.hasNext();) {

            Image image = iterator.next();

            String imgpath = image.getImgpath();

            if(!obj.getContent().contains(imgpath)) {
                
                awsS3Service.delete(image.getImgkey());
                iterator.remove();

            }

        }

        log.info(format, obj.getImageList());

        obj.setWriter(user.getUsername()); // 로그인 상태에서 받아올 작성자 이메일 정보

        int ret = pService.insertPostOne(obj);
        
        if(ret == 1) {
            retMap.put("status", 200);

            // 팔로워 목록 -> 글 작성 알림
            String title = (obj.getTitle().length() > 7) ? obj.getTitle().substring(0, 7) + "..." : obj.getTitle();
            String content = "<b>" + WjmService.findByEmail(obj.getWriter()).getNickname() + "</b>님이 새 글 [" + title + "]을 작성하셨습니다.";
            String url = "/blog/" + obj.getWriter() + "/select.do?postno=" + obj.getNo();
            List<String> followerlist = WjaService.selectFollowerList(obj.getWriter());
            for (String follower : followerlist) {
                WjaService.alertInsert(follower, content, "팔로잉게시글", url, obj.getRegdate());
            }
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
