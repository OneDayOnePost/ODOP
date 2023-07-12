package com.example.dto;

import java.math.BigInteger;

import lombok.Data;

// 좋아요
@Data
public class DopeDTO {

  // 번호(시퀀스)
  private BigInteger no;

  // 이메일
  private String email;

  // 게시글 번호(시퀀스)
  private BigInteger postno;
  
}
