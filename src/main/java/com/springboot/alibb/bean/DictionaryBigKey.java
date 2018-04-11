package com.springboot.alibb.bean;

import java.io.Serializable;

public class DictionaryBigKey implements Serializable {
    private String dictType;

    private String dictName;

    private static final long serialVersionUID = 1L;

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType == null ? null : dictType.trim();
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }
}