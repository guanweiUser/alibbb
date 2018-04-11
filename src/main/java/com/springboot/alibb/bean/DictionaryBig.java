package com.springboot.alibb.bean;

import java.io.Serializable;

public class DictionaryBig extends DictionaryBigKey implements Serializable {
    private String dictValue;

    private static final long serialVersionUID = 1L;

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue == null ? null : dictValue.trim();
    }
}