package com.example.dto;

import java.math.BigInteger;

import lombok.Data;

// 게시글 신고 사유
@Data
public class PostReasonDTO {

  // 신고 사유 번호(시퀀스)
  private BigInteger no;

  // 신고 사유
  private String reason;
}
