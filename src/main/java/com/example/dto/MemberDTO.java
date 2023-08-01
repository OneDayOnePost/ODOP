package com.example.dto;

import java.math.BigInteger;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

// 멤버
@Data
public class MemberDTO {
  // 이메일
  private String email;
  // 암호
  @ToString.Exclude
  private String password;
  // 이름
  private String name;
  // 연락처(이메일 찾기용)
  private String phone;
  // 닉네임
  private String nickname;
  // mbti
  private String mbti;
  // 가입일
  private Date regdate;
  // 프로필 이미지
  @ToString.Exclude
  private byte[] filedata;
  // 프로필 이미지 크기
  private BigInteger filesize;
  // 프로필 이미지명
  private String filename;
  // 프로필 이미지타입
  private String filetype;
  // 소개
  private String introduce;
  // 블로그명
  private String blogname;
  // 탈퇴유무(-1:강제탈퇴 / 0:탈퇴X / 1:탈퇴O)
  private BigInteger quitchk;
  // C:고객 / A:관리자
  private String role;
}
