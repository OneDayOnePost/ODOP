package com.example.mapper.WJ;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.entity.Cate;

@Mapper
public interface WjCateMapper {
    // 카테고리 불러오기
    @Select(" SELECT * FROM CATE c ")
    public List<Cate> selectCateList();
}
