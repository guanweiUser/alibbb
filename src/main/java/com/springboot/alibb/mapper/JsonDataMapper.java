package com.springboot.alibb.mapper;

import com.springboot.alibb.bean.JsonData;
import com.springboot.alibb.bean.JsonDataExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface JsonDataMapper {
    long countByExample(JsonDataExample example);

    int deleteByExample(JsonDataExample example);

    int deleteByPrimaryKey(String id);

    int insert(JsonData record);

    int insertSelective(JsonData record);

    List<JsonData> selectByExample(JsonDataExample example);

    JsonData selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") JsonData record, @Param("example") JsonDataExample example);

    int updateByExample(@Param("record") JsonData record, @Param("example") JsonDataExample example);

    int updateByPrimaryKeySelective(JsonData record);

    int updateByPrimaryKey(JsonData record);
}