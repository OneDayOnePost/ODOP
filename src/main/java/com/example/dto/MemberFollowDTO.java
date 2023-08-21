package com.example.dto;

import java.math.BigInteger;

import lombok.Data;

// 멤버
@Data
public class MemberFollowDTO {
  // 이메일
  private String email;
  // 프로필 이미지 키
  private String imgkey;
  // 프로필 이미지 경로
  private String imgpath;
  // 로그인한 유저 -> 해당 이메일 팔로우 유무
  // 팔로우 o : 1 / 팔로우 x : 0
  private BigInteger follow_yumu;

  // 닉네임
  private String nickname;
}
