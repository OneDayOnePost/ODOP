package com.example.dto;

import java.math.BigInteger;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

// 댓글
@Data
public class ReplyDTO {

  // 댓글 번호(시퀀스)
  private BigInteger no;

  // 작성자
  private String writer;

  // 게시글 번호(시퀀스)
  private BigInteger postno;

  // 내용
  private String content;

  // 작성일
  private Date regdate;

  @ToString.Exclude
  private byte[] filedata;

  private BigInteger filesize;

  private String filename;

  private String filetype;

  // 비밀댓글 여부(0: 비밀글 X / 1: 비밀글 O)
  private BigInteger secret;

  // 상태(-1: 관리자 삭제 / 0:삭제X / 1: 작성자 삭제)
  private BigInteger state;

  // 깊이(댓글 : 0, 답글 : 1)
  private BigInteger repdepth;

  // 순서(부모 댓글 : 0)
  private BigInteger reporder;

  // 댓글 그룹(부모 댓글 번호)
  private BigInteger repgroup;
  
}
