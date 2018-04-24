package com.springboot.alibb.web.vo;/**
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @author Mr.Guan
 * @Mail GuanWeiMail@163.com
 * @date 2018-04-2416:24
 */

import com.springboot.alibb.bean.AliUser;

/**
 * @program: alibbb
 *
 * @description:
 *
 * @author: Mr.Guan
 *
 * @Mail: GuanWeiMail@163.com
 *
 * @create: 2018-04-24 16:24
 **/
public class AliUserVo extends AliUser {

    /**
     * 每页几条
     */
    private int limit = 10;
    /**
     * 第几页
     */
    private int offset = 0;

    /**
     * jsonp专用回调
     */
    private String callback;

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
}
