package com.example.dto;

import java.math.BigInteger;

import lombok.Data;

// 팔로우
@Data
public class FollowDTO {

  // 팔로우 번호(시퀀스)
  private BigInteger no;

  // 이메일(사용자 이메일)
  private String myid;

  // 이메일(팔로우한 이메일)
  private String yourid;
}
