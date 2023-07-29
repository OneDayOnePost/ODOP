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

// 게시글_해시태그
@Data
@Entity
@Table(name = "POST_TAG")
@SequenceGenerator(name = "SEQ_PTAG_NO", sequenceName = "SEQ_PTAG_NO", initialValue = 1, allocationSize = 1)
public class PostTag {

  // 게시글_해시태그 시퀀스
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PTAG_NO")
  private BigInteger no;

  // 해시태그
  private String tag;

  // 게시글
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "POSTNO", referencedColumnName = "NO")
  private Post post;
}
