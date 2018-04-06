package com.springboot.alibb.web.controller;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.springboot.alibb.service.ISubjectRecordService;
import com.springboot.alibb.web.vo.AppraisalVo;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.springboot.alibb.web.vo.SubjectRecordVo;
import com.springboot.base.common.BaseController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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

    @Resource
    private ISubjectRecordService subjectRecordService;

    /**
     * 1提交测评
     * 2返回测评结果
     * @param request
     * @return
     */
    @RequestMapping("/result")
    public String result(HttpServletRequest request) {


        //测评选项
        String[] results2 = request.getParameterValues("result[]");

        //jsonp
        String callback = request.getParameter("callback");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String age = request.getParameter("age");
        String sex = request.getParameter("sex");

        //抓取地址
        String url = "http://m.geilixinli.com/cs/result/";
        //参数
        JSONObject paramMap = new JSONObject();
        AppraisalVo appraisalVo = new AppraisalVo();
        paramMap.put("result[]",results2);
        paramMap.put("subjectid",27);
        paramMap.put("counttype",3);
        paramMap.put("taskid",0);
        paramMap.put("groupid",0);
        paramMap.put("qyid",0);

        String result = HttpUtil.post(url, paramMap);

        Document html = Jsoup.parse(result);

        Elements r_info = html.getElementsByClass("r_info");
        String s = r_info.html().toString().replaceAll("<", "@xiaoyu").replaceAll(">", "@dayu").replaceAll("\r|\n", "");
//        return callback + "{'html':'" + r_info.html() + "'}";

        SubjectRecordVo subjectRecordVo = new SubjectRecordVo();

        subjectRecordVo.setName(name);
        subjectRecordVo.setPhone(phone);

        if(NumberUtil.isInteger(age)){
            subjectRecordVo.setAge(Integer.valueOf(age));
        }
        subjectRecordVo.setSex(sex);
        subjectRecordVo.setResult(JSONUtil.parse(results2).toString());

        subjectRecordVo.setIp(this.getIpAddr(request));

        //添加信息到数据库
        subjectRecordService.addSubjectRecord(subjectRecordVo);

        return callback + "({'html':'"+s+"'})";
    }


}