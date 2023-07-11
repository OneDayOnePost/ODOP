package com.example.dto;

import java.math.BigInteger;

import lombok.Data;

// 게시글_해시태그
@Data
public class PostTagDTO {

  // 게시글_해시태그 시퀀스
  private BigInteger no;

  // 게시글 번호(시퀀스)
  private BigInteger postno;

  // 해시태그
  private String tag;
  
}
