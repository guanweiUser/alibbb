package com.springboot.alibb.service;

import com.springboot.alibb.web.vo.SubjectRecordVo;
import org.springframework.stereotype.Service;

/**
 * 测评
 */
@Service
public interface ISubjectRecordService {

    public String addSubjectRecord(SubjectRecordVo SubjectRecordVo);

}
