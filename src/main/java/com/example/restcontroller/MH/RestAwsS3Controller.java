package com.example.restcontroller.MH;

import java.io.IOException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.AwsS3DTO;
import com.example.service.MH.AwsS3Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/s3")
@RequiredArgsConstructor
public class RestAwsS3Controller {
    
    private final AwsS3Service awsS3Service;

    @PostMapping("/upload")
    public AwsS3DTO upload(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        return awsS3Service.upload(multipartFile,"upload");
    }

    @DeleteMapping("/remove")
    public void remove(@RequestBody AwsS3DTO awsS3) {

        // log.info("RestAwsS3Controller => {}", awsS3.toString());

        awsS3Service.remove(awsS3);
    }

}
