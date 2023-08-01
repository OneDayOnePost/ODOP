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

  // 상태(-1: 관리자 삭제 / 0:삭제X / 1: 작성자 삭제)
  private BigInteger state;
  
}
