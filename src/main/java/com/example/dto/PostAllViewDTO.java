package com.example.dto;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

// 홈 게시글 조회 뷰
@Data
public class PostAllViewDTO {

    // 게시글 번호(시퀀스)
    private BigInteger no;

    // 작성자
    private String writer;

    // 제목
    private String title;

    // 내용
    private String content;

    // 조회수
    private BigInteger hit;

    // 작성일
    private Date regdate;

    public String regdate1() {
        if (regdate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            return sdf.format(regdate);
        } else {
            return null;
        }
    }

    // 카테고리 번호(시퀀스
    private BigInteger cateno;

    // 비밀글 여부(0: 비밀글 X / 1: 비밀글 O)
    private BigInteger secret;

    // 상태(-1: 관리자 삭제 / 0:삭제X / 1: 작성자 삭제)
    private BigInteger state;

    // 마크다운
    private String markdown;

    // 좋아요 (카운트)
    private int dope_count;
    
    public String dope_count1() {
        if (dope_count >= 0 && dope_count <= 9) {
            return "0" + dope_count;
        } else {
            return Integer.toString(dope_count);
        }
    }

    // 닉네임
    private String nickname;

    // mbti
    private String mbti;

    // 프로필 이미지
    private String imgkey;
    private String imgpath;

    // 게시글 대표 이미지
    private String p_imgkey;
    private String p_imgpath;
    
}
