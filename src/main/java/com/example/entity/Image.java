package com.example.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

// 이미지
@Data
@Entity
@Table(name = "IMAGE")
@SequenceGenerator(name = "SEQ_IMAGE_NO", sequenceName = "SEQ_IMAGE_NO", initialValue = 1, allocationSize = 1)
public class Image {

  // 이미지 번호(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_IMAGE_NO")
  private BigInteger no;
  
  @Lob
  @ToString.Exclude
  private byte[] filedata;

  private BigInteger filesize;

  private String filename;

  private String filetype;

  // 대표이미지(0:X / 1:O)
  private BigInteger chk;

  // 게시글
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "postno", referencedColumnName = "no")
  private Post post;
}
