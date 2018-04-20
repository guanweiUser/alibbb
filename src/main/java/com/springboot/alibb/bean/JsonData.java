package com.springboot.alibb.bean;

import java.io.Serializable;
import java.util.Date;

public class JsonData implements Serializable {
    private String id;

    private String jsonDitch;

    private String jsonType;

    private String jsonData;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getJsonDitch() {
        return jsonDitch;
    }

    public void setJsonDitch(String jsonDitch) {
        this.jsonDitch = jsonDitch == null ? null : jsonDitch.trim();
    }

    public String getJsonType() {
        return jsonType;
    }

    public void setJsonType(String jsonType) {
        this.jsonType = jsonType == null ? null : jsonType.trim();
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData == null ? null : jsonData.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}