package com.example.dto;

import java.math.BigInteger;

import lombok.Data;

@Data
public class PostImageDTO {

    // 번호(시퀀스)
    private BigInteger no;
    // 이미지 번호(시퀀스)
    private BigInteger imageno;
    // 게시글 번호(시퀀스)
    private BigInteger postno;
    
}
