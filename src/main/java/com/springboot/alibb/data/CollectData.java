package com.springboot.alibb.data;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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

        String result = CollectData.xinliceping();
        System.out.println(result);


    }


    /**
     *
     * @return
     */
    public static String xinliceping() {

        //抓取地址
        String url = "http://m.geilixinli.com/cs/result/";

        String body = "result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=4&result[]=4&result[]=4&result[]=4&result[]=4&result[]=4&result[]=4&result[]=4&result[]=4&result[]=2&result[]=2&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=1&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=3&result[]=4&result[]=4&result[]=4&result[]=4&result[]=4&result[]=4&result[]=4&result[]=2&result[]=3&subjectid=27&counttype=3&taskid=0&groupid=0&qyid=0&testuserinfo=";

        //参数
        JSONObject paramMap = new JSONObject();

        JSONArray resultsParam = new JSONArray();
        for (int i = 0; i < 90; i++){
            int inx = (int)(1+Math.random()*(5));
//            System.out.println(inx);
            resultsParam.add(inx);
        }


        paramMap.put("result[]",resultsParam);
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
}
