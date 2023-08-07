package com.example.mapper.WJ;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WjCateMapper {
    // 카테고리 불러오기
    @Select(" SELECT CATEGORY FROM CATE c ")
    public List<String> selectCateList();
}
