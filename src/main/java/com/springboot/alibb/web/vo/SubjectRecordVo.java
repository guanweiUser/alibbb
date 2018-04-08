package com.springboot.alibb.web.vo;

import com.springboot.alibb.bean.SubjectRecord;

import java.util.List;

public class SubjectRecordVo extends SubjectRecord {

    /**
     * 用户填写测评结果
     */
    private List<String> userResult;

    /**
     * jsonp专用回调
     */
    private String callback;

    public List<String> getUserResult() {
        return userResult;
    }

    public void setUserResult(List<String> userResult) {
        this.userResult = userResult;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}
