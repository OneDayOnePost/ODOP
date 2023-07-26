package com.example.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

// 게시글-이미지 연결
@Data
@Entity
@Table(name = "POST_IMAGE")
@SequenceGenerator(name = "SEQ_POST_IMAGE_NO", sequenceName = "SEQ_POST_IMAGE_NO", initialValue = 1, allocationSize = 1)
public class PostImage {

  // 번호(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_POST_IMAGE_NO")
  private BigInteger no;

  // 이미지
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "imageno", referencedColumnName = "no")
  private Image image;

  // 게시글
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "postno", referencedColumnName = "no")
  private Post post;

}
