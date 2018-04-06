package com.springboot.alibb.service.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.springboot.alibb.mapper.SubjectRecordMapper;
import com.springboot.alibb.service.ISubjectRecordService;
import com.springboot.alibb.web.vo.SubjectRecordVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 答题（测评）记录
 */
@Service
class SubjectRecordServiceImpl implements ISubjectRecordService {

    //推荐创建不可变静态类成员变量
    private static final Log log = LogFactory.get();

    @Resource
    private SubjectRecordMapper subjectRecordMapper;

    /**
     * 提交测评
     * @return
     */
    @Override
    public String addSubjectRecord(SubjectRecordVo subjectRecordVo) {
        subjectRecordVo.setId(SecureUtil.simpleUUID());
        subjectRecordVo.setCreateTime(new Date());
        subjectRecordMapper.insert(subjectRecordVo);
        log.info("姓名-手机号：{}-{}，提交了测评信息!", subjectRecordVo.getName(), subjectRecordVo.getPhone());
        return null;
    }
}
