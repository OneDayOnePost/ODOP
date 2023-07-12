package com.example.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

// 댓글 신고 사유
@Data
@Entity
@Table(name = "REPLY_REASON")
@SequenceGenerator(name = "SEQ_RREASON_NO", sequenceName = "SEQ_RREASON_NO", initialValue = 1, allocationSize = 1)
public class ReplyReason {
  // 신고 사유 번호(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RREASON_NO")
  private BigInteger no;

  // 신고 사유
  @Column(nullable = false)
  private String reason;
  
  // 댓글 신고
  @ToString.Exclude
  @OneToMany(mappedBy = "replyReason", fetch = FetchType.LAZY)
  private List<ReplyReport> replyReportList = new ArrayList<>();
}
