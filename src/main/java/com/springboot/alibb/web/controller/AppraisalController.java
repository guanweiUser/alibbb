package com.springboot.alibb.web.controller;

import cn.hutool.json.JSONUtil;
import com.springboot.alibb.service.ISubjectRecordService;
import com.springboot.alibb.web.vo.SubjectRecordVo;
import com.springboot.base.common.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 测评
 *
 * @author GuanWeiMail@163.com
 */
@RestController
@RequestMapping("/appraisal")
public class AppraisalController extends BaseController {

    /**
     * 测评service
     */
    @Resource
    private ISubjectRecordService subjectRecordService;

    /**
     * 1提交测评
     * 2返回测评结果
     * @param request
     * @return
     */
    @RequestMapping("/result")
    public String result(HttpServletRequest request, SubjectRecordVo subjectRecordVo) {

        try {

            //测评人IP
            subjectRecordVo.setIp(this.getIpAddr(request));
            //测评人浏览器信息
            subjectRecordVo.setBrowserInfo(this.getBrowserInfo(request));

            //解析测评 获取结果 存储测评信息 返回测评结果
            return subjectRecordService.addSubjectRecord(subjectRecordVo);

        } catch (Exception e) {
            e.printStackTrace();
            return "系统故障，请稍后再试!";
        }
    }


    /**
     * 获取填报测评历史信息
     * @param request
     * @return
     */
    @RequestMapping("/getSubjectRecordList.json")
    public String getSubjectRecordList(HttpServletRequest request, SubjectRecordVo subjectRecordVo) {

        try {

            //测评人IP
            subjectRecordVo.setIp(this.getIpAddr(request));
            //测评人浏览器信息
            subjectRecordVo.setBrowserInfo(this.getBrowserInfo(request));

            //解析测评 获取结果 存储测评信息 返回测评结果
            return subjectRecordVo.getCallback() + "(" + subjectRecordService.getSubjectRecordList(subjectRecordVo).toString() + ")";

        } catch (Exception e) {
            e.printStackTrace();
            return "系统故障，请稍后再试!";
        }
    }
    /**
     * 查看某人测评结果信息
     * @param request
     * @return
     */
    @RequestMapping("/getSubjectRecordResultById.json")
    public String getSubjectRecordResultById(HttpServletRequest request, SubjectRecordVo subjectRecordVo) {

        try {

            //测评人IP
            subjectRecordVo.setIp(this.getIpAddr(request));
            //测评人浏览器信息
            subjectRecordVo.setBrowserInfo(this.getBrowserInfo(request));

            //解析测评 获取结果 存储测评信息 返回测评结果
            return subjectRecordVo.getCallback() + "(" + JSONUtil.parse(subjectRecordService.getSubjectRecordResultById(subjectRecordVo)).toString() + ")";

        } catch (Exception e) {
            e.printStackTrace();
            return "系统故障，请稍后再试!";
        }
    }


}