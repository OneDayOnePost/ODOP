package com.example.dto;

import java.math.BigInteger;

import lombok.Data;

// 댓글 신고
@Data
public class ReplyReportDTO {

  // 댓글 신고 번호(시퀀스)
  private BigInteger no;

  // 이메일
  private String email;

  // 댓글 번호(시퀀스)
  private BigInteger replyno;

  // 신고 사유 번호(시퀀스)
  private BigInteger reasonno;
  
}
