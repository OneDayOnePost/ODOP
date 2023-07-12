package com.example.dto;

import java.math.BigInteger;
import java.util.Date;

import lombok.Data;

// 알림
@Data
public class AlertDTO {

  // 알림 번호(시퀀스)
  private BigInteger no;

  // 이메일(받는 사람, 로그인된 사람)
  private String email;

  // 알림 내용
  private String content;

  // 알림 분류
  private String type;

  // 알림 이동 주소
  private String url;

  // 0:확인 X / 1:확인 O
  private BigInteger chk;

  // 등록 날짜
  private Date regdate;
  
}
