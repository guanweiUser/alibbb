package com.springboot.alibb.web.controller;

import com.springboot.alibb.web.vo.AppraisalVo;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 测评
 *
 * @author GuanWeiMail@163.com
 */
@RestController
@RequestMapping("/appraisal")
public class AppraisalController {


    @RequestMapping("/result")
    public String result(HttpServletRequest request) {





        String[] results2 = request.getParameterValues("result[]");
        String callback = request.getParameter("callback");

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
        return callback + "({'html':'"+s+"'})";
    }


}