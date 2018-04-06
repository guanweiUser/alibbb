package com.springboot.alibb.mapper;

import com.springboot.alibb.bean.SubjectRecord;
import com.springboot.alibb.bean.SubjectRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubjectRecordMapper {
    long countByExample(SubjectRecordExample example);

    int deleteByExample(SubjectRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(SubjectRecord record);

    int insertSelective(SubjectRecord record);

    List<SubjectRecord> selectByExample(SubjectRecordExample example);

    SubjectRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SubjectRecord record, @Param("example") SubjectRecordExample example);

    int updateByExample(@Param("record") SubjectRecord record, @Param("example") SubjectRecordExample example);

    int updateByPrimaryKeySelective(SubjectRecord record);

    int updateByPrimaryKey(SubjectRecord record);
}