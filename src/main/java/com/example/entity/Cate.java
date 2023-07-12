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

// 카테고리
@Data
@Entity
@Table(name = "CATE")
@SequenceGenerator(name = "SEQ_CATE_NO", sequenceName = "SEQ_CATE_NO", initialValue = 1, allocationSize = 1)
public class Cate {
    
  // 카테고리 번호(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CATE_NO")
  private BigInteger no;

  // 카테고리
  private String category;

  // 게시글
  @ToString.Exclude
  @OneToMany(mappedBy = "cate", fetch = FetchType.LAZY)
  private List<Post> postList = new ArrayList<>();
  
}
