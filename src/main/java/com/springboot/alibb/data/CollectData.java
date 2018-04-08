package com.springboot.alibb.data;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.springboot.alibb.web.vo.AppraisalVo;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @program: spring-cloud-parent
 *
 * @description:抓取数据
 *
 * @author: Mr.Guan
 *
 * @Mail: GuanWeiMail@163.com
 *
 * @create: 2018-04-03 10:59
 **/
public class CollectData {


    public static void main(String[] args) {
        String s = HttpUtil.get("http://www.apesk.com/pressure/ylcs_report.asp?id=22992", Charset.forName("GB2312"));

        System.out.println(s);
    }


    /**
     * 给力心理测评
     * @return
     */
    public static String geilixinliResult(List<String> userResult) {

        //抓取地址
        String url = "http://m.geilixinli.com/cs/result/";
        //参数
        JSONObject paramMap = new JSONObject();
        AppraisalVo appraisalVo = new AppraisalVo();
        paramMap.put("result[]",userResult);
        paramMap.put("subjectid",27);
        paramMap.put("counttype",3);
        paramMap.put("taskid",0);
        paramMap.put("groupid",0);
        paramMap.put("qyid",0);

        String result = HttpUtil.post(url, paramMap);

        Document html = Jsoup.parse(result);

        Elements r_info = html.getElementsByClass("r_info");

        return r_info.html();
    }


    /**
     * http://www.apesk.com/pressure/
     * @return
     */
    public static String pressureResult(JSONObject paramMap) {

        //抓取地址
        String url = "http://www.apesk.com/pressure/ylcs_submit.asp";

        //提交表单  302跳转
        String result = HttpUtil.post(url, paramMap);

        Document html = Jsoup.parse(result);
        //获取302跳转的路径
        String attr = html.getElementsByTag("a").attr("href");

        //访问改路径
        String s = HttpUtil.get("http://www.apesk.com/pressure/" + attr, Charset.forName("GB2312"));

        Document parse = Jsoup.parse(s);
        Elements iframe = parse.getElementsByTag("iframe");

        for (int i = 0; i < iframe.size(); i++) {

            Element e = iframe.get(i);

            String src = e.attr("src");
            if(src.indexOf("report_logo_m.asp") != -1 || src.indexOf("report_right.asp") != -1){
                e.attr("src", "#");
                e.remove();
//                iframe.remove(i);
//                i--;
            }else{

                e.attr("src", "http://www.apesk.com" + e.attr("src"));
            }
        }

        Elements links = parse.getElementsByTag("link");
        for (int i = 0; i < links.size(); i++) {

            Element e = links.get(i);
            e.attr("href", "http://www.apesk.com" + e.attr("href"));
        }

        Elements scripts = parse.getElementsByTag("script");
        for (int i = 0; i < scripts.size(); i++) {

            Element e = scripts.get(i);
            e.attr("src", "http://www.apesk.com" + e.attr("src"));
        }

        return parse.html().replaceAll("报告接收人:才储YLCS成员", "");
    }













}
