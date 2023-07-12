package com.example.dto;

import java.math.BigInteger;

import lombok.Data;
import lombok.ToString;

// 이미지
@Data
public class ImageDTO {

  // 이미지 번호(시퀀스)
  private BigInteger no;

  @ToString.Exclude
  private byte[] filedata;

  private BigInteger filesize;

  private String filename;

  private String filetype;

  // 게시글 번호(시퀀스)
  private BigInteger postno;

  // 대표이미지(0:X / 1:O)
  private BigInteger chk;
}

