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

// 팔로우
@Data
@Entity
@Table(name = "FOLLOW")
@SequenceGenerator(name = "SEQ_FOLLOW_NO", sequenceName = "SEQ_FOLLOW_NO", initialValue = 1, allocationSize = 1)
public class Follow {

  // 팔로우 번호(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FOLLOW_NO")
  private BigInteger no;

  // 멤버 (팔로우를 요청하는 사람)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "myid", referencedColumnName = "email")
  private Member fromMember;

  // 멤버 (팔로우 요청을 받는 사람)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "yourid", referencedColumnName = "email")
  private Member toMember;

}
