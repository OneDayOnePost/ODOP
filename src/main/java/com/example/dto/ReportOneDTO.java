package com.example.dto;

import java.math.BigInteger;

import lombok.Data;

// 신고 1개 상세 조회
@Data
public class ReportOneDTO {
    private String reason;

    private BigInteger reportcount;
}
