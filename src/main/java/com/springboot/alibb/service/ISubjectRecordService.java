package com.springboot.alibb.service;

import cn.hutool.json.JSONObject;
import com.springboot.alibb.bean.SubjectRecord;
import com.springboot.alibb.web.vo.SubjectRecordVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测评
 */
@Service
public interface ISubjectRecordService {

    /**
     * 添加测评信息
     * @param subjectRecordVo
     * @return
     */
    public String addSubjectRecord(SubjectRecordVo subjectRecordVo);


    /**
     * 获取测评历史记录
     * @param subjectRecordVo
     * @return
     */
    public JSONObject getSubjectRecordList(SubjectRecordVo subjectRecordVo) throws Exception;

    /**
     * 查看某人测评结果信息
     * @param subjectRecordVo
     * @return
     */
    public SubjectRecord getSubjectRecordResultById(SubjectRecordVo subjectRecordVo);
}
