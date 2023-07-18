package com.example.entity.GR;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Immutable
@Table(name = "POSTALL")
public class postall {

    @Id
    private BigInteger no;

    private String writer;

    private String title;

    @Lob
    private String content;

    private BigInteger hit;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date regdate;

    private BigInteger cateno;

    private BigInteger secret;

    private BigInteger state;

    private String role;

    private BigInteger tagno;

    private BigInteger postno;

    private String tag;

}
