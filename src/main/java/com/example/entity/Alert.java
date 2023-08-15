package com.example.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

// 알림
@Data
@Entity
@Table(name = "ALERT")
@SequenceGenerator(name = "SEQ_ALERT_NO", sequenceName = "SEQ_ALERT_NO", initialValue = 1, allocationSize = 1)
public class Alert {

    // 알림 번호(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ALERT_NO")
  private BigInteger no;

  // 이메일(받는 사람, 로그인된 사람)
  private String email;

  // 알림 내용
  private String content;

  // 알림 분류
  private String type;

  // 알림 이동 주소
  private String url;
  
  // 0:확인 X / 1:확인 O
  private BigInteger chk;

  // 등록 날짜
  @CreationTimestamp
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @Column(name = "REGDATE", insertable = true, updatable = false)
  private Date regdate;

  // 알림 보낸 사람 
  // 프로필 이미지 키를 저장할 임시변수
  @Transient
  private String imgkey;
  // 프로필 이미지 경로를 저장할 임시변수
  @Transient
  private String imgpath;
}
