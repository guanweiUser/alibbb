package com.springboot.alibb.web.vo;

import com.springboot.alibb.bean.SubjectRecord;

import java.util.List;

public class SubjectRecordVo extends SubjectRecord {

    /**
     * 每页几条
     */
    private int limit = 10;
    /**
     * 第几页
     */
    private int offset = 0;
    /**
     * 浏览器信息
     */
    private String browserInfo;

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

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getBrowserInfo() {
        return browserInfo;
    }

    public void setBrowserInfo(String browserInfo) {
        this.browserInfo = browserInfo;
    }
}
