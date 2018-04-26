package com.springboot.alibb.service;

import cn.hutool.json.JSONObject;
import com.springboot.alibb.bean.AliUser;
import com.springboot.alibb.bean.SubjectRecord;
import com.springboot.alibb.web.vo.AliUserVo;
import com.springboot.alibb.web.vo.SubjectRecordVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户
 */
@Service
public interface IAliUserService {

    /**
     * 获取用户列表
     * @param aliUserVo
     * @return
     */
    public JSONObject getAliUserList(AliUserVo aliUserVo) throws Exception;

}
