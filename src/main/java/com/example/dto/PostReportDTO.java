package com.example.dto;

import java.math.BigInteger;

import lombok.Data;

// 게시글 신고
@Data
public class PostReportDTO {

  // 신고 번호(시퀀스)
  private BigInteger no;

  // 이메일
  private String email;

  // 게시글 번호(시퀀스)
  private BigInteger postno;

  // 신고 사유 번호(시퀀스)
  private BigInteger reasonno;
  
}
