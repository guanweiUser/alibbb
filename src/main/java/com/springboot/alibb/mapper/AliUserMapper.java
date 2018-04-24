package com.springboot.alibb.mapper;

import com.springboot.alibb.bean.AliUser;
import com.springboot.alibb.bean.AliUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AliUserMapper {
    long countByExample(AliUserExample example);

    int deleteByExample(AliUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(AliUser record);

    int insertSelective(AliUser record);

    List<AliUser> selectByExample(AliUserExample example);

    AliUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AliUser record, @Param("example") AliUserExample example);

    int updateByExample(@Param("record") AliUser record, @Param("example") AliUserExample example);

    int updateByPrimaryKeySelective(AliUser record);

    int updateByPrimaryKey(AliUser record);
}