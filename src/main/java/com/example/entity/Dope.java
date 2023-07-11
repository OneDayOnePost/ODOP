package com.example.entity;

import java.math.BigInteger;

import javax.persistence.Column;
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

// 좋아요
@Data
@Entity
@Table(name = "DOPE")
@SequenceGenerator(name = "SEQ_LIKE_NO", sequenceName = "SEQ_LIKE_NO", initialValue = 1, allocationSize = 1)
public class Dope {

  // 번호(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LIKE_NO")
  private BigInteger no;

  // 이메일
  @Column(nullable = false)
  private String email;
  
  // 게시글
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "postno", referencedColumnName = "no")
  private Post post;
}
