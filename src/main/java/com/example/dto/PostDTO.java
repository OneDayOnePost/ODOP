package com.example.dto;

import java.math.BigInteger;
import java.util.Date;

import lombok.Data;

// 게시글
@Data
public class PostDTO {

  // 게시글 번호(시퀀스)
  private BigInteger no;

  // 작성자
  private String writer;

  // 제목
  private String title;

  // 내용
  private String content;

  // 조회수
  private BigInteger hit;

  // 작성일
  private Date regdate;

  // 카테고리 번호(시퀀스
  private BigInteger cateno;

  // 비밀글 여부(0: 비밀글 X / 1: 비밀글 O)
  private BigInteger secret;

  // 상태(0:신고X / 1:신고O)
  private BigInteger state;

  // 권한(C:멤버 / A:관리자)
  private String role;
  
}
