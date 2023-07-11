package com.example.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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

// 게시글 신고 사유
@Data
@Entity
@Table(name = "POST_REASON")
@SequenceGenerator(name = "SEQ_PREASON_NO", sequenceName = "SEQ_PREASON_NO", initialValue = 1, allocationSize = 1)
public class PostReason {
  // 신고 사유 번호(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PREASON_NO")
  private BigInteger no;

  // 신고 사유
  private String reason;

  // 게시글 신고
  @ToString.Exclude
  @OneToMany(mappedBy = "postReason", fetch = FetchType.LAZY)
  private List<PostReport> postReportList = new ArrayList<>();
}
