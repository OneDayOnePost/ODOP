package com.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AwsS3DTO {

    private String key;
    private String path;

    @Builder
    public AwsS3DTO(String key, String path) {
        this.key = key;
        this.path = path;
    }
    
}
