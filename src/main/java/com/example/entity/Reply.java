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
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

// 댓글
@Data
@Entity
@Table(name = "REPLY")
@SequenceGenerator(name = "SEQ_REPLY_NO", sequenceName = "SEQ_REPLY_NO", initialValue = 1, allocationSize = 1)
public class Reply {
  // 댓글 번호(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REPLY_NO")
  private BigInteger no;

  // 작성자
  @Column(nullable = false)
  private String writer;

  // 내용
  private String content;

  // 작성일
  @CreationTimestamp
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @Column(name = "REGDATE", insertable = true, updatable = false)
  private Date regdate;

  @Lob
  @ToString.Exclude
  private byte[] filedata;

  private BigInteger filesize;

  private String filename;

  private String filetype;

  // 비밀댓글 여부(0: 비밀글 X / 1: 비밀글 O)
  private BigInteger secret;

  // 상태(0:신고X / 1:신고O)
  private BigInteger state = new BigInteger("0");

  // 깊이(댓글 : 0, 답글 : 1)
  private BigInteger repdepth = new BigInteger("0");

  // 순서(부모 댓글 : 0)
  private BigInteger reporder = new BigInteger("0");

  // 댓글 그룹(부모 댓글 번호)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REPLY_NO")
  @Generated(GenerationTime.INSERT)
  private BigInteger repgroup;

  // 게시글
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @JoinColumn(name = "postno", referencedColumnName = "no")
  private Post post;

  // 댓글 신고
  @ToString.Exclude
  @OneToMany(mappedBy = "reply", fetch = FetchType.LAZY)
  private List<ReplyReport> replyReportList = new ArrayList<>();
}
