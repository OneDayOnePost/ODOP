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

// 댓글 신고
@Data
@Entity
@Table(name = "REPLY_REPORT")
@SequenceGenerator(name = "SEQ_RREPORT_NO", sequenceName = "SEQ_RREPORT_NO", initialValue = 1, allocationSize = 1)
public class ReplyReport {
  // 댓글 신고 번호(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RREPORT_NO")
  private BigInteger no;

  // 이메일
  @Column(nullable = false)
  private String email;

  // 댓글
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "replyno", referencedColumnName = "no")
  private Reply reply;

  // 댓글 신고 사유
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "reasonno", referencedColumnName = "no")
  private ReplyReason replyReason;
}
