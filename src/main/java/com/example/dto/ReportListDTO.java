package com.example.dto;

import java.math.BigInteger;

import lombok.Data;

// 게시글 신고 리스트
@Data
public class ReportListDTO {
    private BigInteger no;

    private String email;

    private BigInteger reportcount;
}
