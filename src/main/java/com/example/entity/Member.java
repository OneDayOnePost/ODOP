package com.example.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

// 멤버
@Data
@Entity
@Table(name = "MEMBER")
public class Member {
  // 이메일
  @Id
  private String email;

  // 암호
  @Column(nullable = false)
  @ToString.Exclude
  private String password;

  // 이름
  private String name;

  // 연락처(이메일 찾기용)
  private String phone;

  // 닉네임
  private String nickname;

  // mbti
  private String mbti;

  // 가입일
  @CreationTimestamp
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @Column(name = "REGDATE", insertable = true, updatable = false)
  private Date regdate;

  // 프로필 이미지
  @Lob
  @ToString.Exclude
  private byte[] filedata;

  // 프로필 이미지 크기
  private BigInteger filesize;

  // 프로필 이미지명
  private String filename;

  // 프로필 이미지타입
  private String filetype;

  // 프로필 이미지 키
  private String imgkey;

  // 프로필 이미지 경로
  private String imgpath;

  // 소개
  private String introduce;

  // 블로그명
  private String blogname;

  // 탈퇴유무(-1:강제탈퇴 / 0:탈퇴O / 1:탈퇴X)
  private BigInteger quitchk = new BigInteger("1");

  // C:고객 / A:관리자
  private String role="C";

  // 팔로우
  @ToString.Exclude
  @OneToMany(mappedBy = "fromMember", fetch = FetchType.LAZY)
  private List<Follow> followerList = new ArrayList<>();

  // 팔로우
  @ToString.Exclude
  @OneToMany(mappedBy = "toMember", fetch = FetchType.LAZY)
  private List<Follow> followingList = new ArrayList<>();
}
