package com.example.dto;

import java.math.BigInteger;
import java.util.Date;

import lombok.Data;

// 멤버
@Data
public class MemberListViewDTO {
  // 이메일
  private String email;
  // 이름
  private String name;
  // 연락처(이메일 찾기용)
  private String phone;
  // 닉네임
  private String nickname;
  // 탈퇴유무(-1:강제탈퇴 / 0:탈퇴X / 1:탈퇴O)
  private BigInteger quitchk;
  // 가입일
  private Date regdate;

  // 관리자 삭제된 게시글 수
  private BigInteger postreportcount;
  // 관리자 삭제된 댓글 수
  private BigInteger replyreportcount;
  // 관리자 삭제된 (게시글 수 + 댓글 수)
  private BigInteger totalreportcount;
}
