package com.springboot.alibb.web.controller;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.springboot.alibb.data.CollectData;
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

        //最终返回结果
        String resultHtml = "";

        //题型
        String subjectType = request.getParameter("subjectType");
        String s = null;

        //json提报以及结果信息
        JSONObject jsonResult = new JSONObject();
        switch (subjectType){

            case "geilixinli_90" :  //给力心理90道题
                //测评选项
                String[] results2 = request.getParameterValues("result[]");
                //获取结果
                String r_info = CollectData.geilixinliResult(results2);
                s = r_info.replaceAll("<", "@xiaoyu").replaceAll(">", "@dayu").replaceAll("\r|\n", "");

                //存储结果信息
                jsonResult.put("select", results2);
                jsonResult.put("result", r_info);
                //jsonp
                String callback = request.getParameter("callback");
                resultHtml = callback + "({'html':'"+s+"'})";
                break;


            case "pressure_PSTR" :
                //参数
                JSONObject paramMap = new JSONObject();
                for (int i = 1; i <= 50; i++){
                    String rd = request.getParameter("Rd_" + i);
                    paramMap.put("Rd_" + i, rd);
                }

                paramMap.put("tishu", 50);
                String s1 = CollectData.pressureResult(paramMap);
                resultHtml = s1;
                //存储结果信息
                jsonResult.put("select", paramMap);
                jsonResult.put("result", resultHtml);
                break;

        }



        //获取填报的基础信息
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String age = request.getParameter("age");
        String sex = request.getParameter("sex");


        SubjectRecordVo subjectRecordVo = new SubjectRecordVo();

        subjectRecordVo.setType(subjectType);
        subjectRecordVo.setName(name);
        subjectRecordVo.setPhone(phone);

        if(NumberUtil.isInteger(age)){
            subjectRecordVo.setAge(Integer.valueOf(age));
        }
        subjectRecordVo.setSex(sex);


        //存储提报以及结果信息
        subjectRecordVo.setResult(jsonResult.toString());


        //浏览器信息
        subjectRecordVo.setIp(this.getIpAddr(request));
        StringBuilder sb = new StringBuilder();
        String agent=request.getHeader("User-Agent").toLowerCase();
        sb.append("User-Agent：");
        sb.append(agent);

        //客户端电脑的名字
        String remoteHost = request.getRemoteHost();
        sb.append("；remoteHost：");
        sb.append(remoteHost);

        String remoteAddr = request.getRemoteAddr();
        sb.append("；remoteAddr：");
        sb.append(remoteAddr);


        subjectRecordVo.setBrowserInfo(sb.toString());
        //添加信息到数据库
        subjectRecordService.addSubjectRecord(subjectRecordVo);

        return resultHtml;
    }


}