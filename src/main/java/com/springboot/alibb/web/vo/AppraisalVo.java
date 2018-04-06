package com.springboot.alibb.web.vo;

/**
 * @program: spring-cloud-parent
 *
 * @description:
 *
 * @author: Mr.Guan
 *
 * @Mail: GuanWeiMail@163.com
 *
 * @create: 2018-04-03 15:09
 **/
public class AppraisalVo {

    /**
     * 答题信息
     */
    private String[] result;

    /**
     * 题库ID
     */
    private String subjectid;
    private String counttype;
    private String taskid;
    private String groupid;
    private String qyid;


    public String[] getResult() {
        return result;
    }

    public void setResult(String[] result) {
        this.result = result;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public String getCounttype() {
        return counttype;
    }

    public void setCounttype(String counttype) {
        this.counttype = counttype;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getQyid() {
        return qyid;
    }

    public void setQyid(String qyid) {
        this.qyid = qyid;
    }





}
