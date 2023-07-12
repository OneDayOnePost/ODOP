package com.example.dto;

import java.math.BigInteger;

import lombok.Data;

// 카테고리
@Data
public class CateDTO {

  // 카테고리 번호(시퀀스
  private BigInteger no;
  
  // 카테고리
  private String category;

}
