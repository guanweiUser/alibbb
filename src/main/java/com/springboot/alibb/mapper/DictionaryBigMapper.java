package com.springboot.alibb.mapper;

import com.springboot.alibb.bean.DictionaryBig;
import com.springboot.alibb.bean.DictionaryBigExample;
import com.springboot.alibb.bean.DictionaryBigKey;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DictionaryBigMapper {
    long countByExample(DictionaryBigExample example);

    int deleteByExample(DictionaryBigExample example);

    int deleteByPrimaryKey(DictionaryBigKey key);

    int insert(DictionaryBig record);

    int insertSelective(DictionaryBig record);

    List<DictionaryBig> selectByExampleWithBLOBs(DictionaryBigExample example);

    List<DictionaryBig> selectByExample(DictionaryBigExample example);

    DictionaryBig selectByPrimaryKey(DictionaryBigKey key);

    int updateByExampleSelective(@Param("record") DictionaryBig record, @Param("example") DictionaryBigExample example);

    int updateByExampleWithBLOBs(@Param("record") DictionaryBig record, @Param("example") DictionaryBigExample example);

    int updateByExample(@Param("record") DictionaryBig record, @Param("example") DictionaryBigExample example);

    int updateByPrimaryKeySelective(DictionaryBig record);

    int updateByPrimaryKeyWithBLOBs(DictionaryBig record);
}