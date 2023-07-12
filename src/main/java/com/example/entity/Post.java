package com.example.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

// 게시글
@Data
@Entity
@Table(name = "POST")
@SequenceGenerator(name = "SEQ_POST_NO", sequenceName = "SEQ_POST_NO", initialValue = 1, allocationSize = 1)
public class Post {
  // 게시글 번호(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_POST_NO")
  private BigInteger no;

  // 작성자
  @Column(nullable = false)
  private String writer;

  // 제목
  private String title;

  // 내용
  @Lob
  private String content;

  // 조회수
  private BigInteger hit;

  // 작성일
  @CreationTimestamp
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @Column(name = "REGDATE", insertable = true, updatable = false)
  private Date regdate;

  // 비밀글 여부(0: 비밀글 X / 1: 비밀글 O)
  private BigInteger secret;

  // 상태(0:신고X / 1:신고O)
  private BigInteger state;

  // 권한(C:멤버 / A:관리자)
  private String role;

  // 카테고리
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cateno", referencedColumnName = "no")
  private Cate cate;

  // 게시글_해시태그
  @ToString.Exclude
  @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
  private List<PostTag> postTagList = new ArrayList<>();

  // 이미지
  @ToString.Exclude
  @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
  private List<Image> imageList = new ArrayList<>();

  // 댓글
  @ToString.Exclude
  @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
  private List<Reply> replyList = new ArrayList<>();

  // 게시글 신고
  @ToString.Exclude
  @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
  private List<PostReport> postReportList = new ArrayList<>();
  
  // 좋아요
  @ToString.Exclude
  @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
  private List<Dope> dopeList = new ArrayList<>();
}