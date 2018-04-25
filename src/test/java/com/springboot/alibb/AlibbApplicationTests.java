//package com.springboot.alibb;
//
//import cn.hutool.core.util.ArrayUtil;
//import cn.hutool.crypto.SecureUtil;
//import cn.hutool.json.JSONObject;
//import com.springboot.alibb.bean.JsonData;
//import com.springboot.alibb.data.CollectData;
//import com.springboot.alibb.mapper.JsonDataMapper;
//import com.springboot.alibb.mapper.SubjectRecordMapper;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class AlibbApplicationTests {
//
//    @Autowired
//    private JsonDataMapper jsonDataMapper;
//
////    @Test
//    public void contextLoads() {
//
//
//        int min = 1;
//        int max = 9;
//        //模拟所有参数
//        //50道题  每道题5个选项（单选）Rd_1-5
//
//        JSONObject paramMap = new JSONObject();
//        //穷举
//        for (int i = min; i <=max; i++){//每道题5个选项
//            for (int j = min; j <= max; j++) {//每道题5个选项
//
//                //参数
//
//                int result = i + j;
//                if(i != j && result <= max && i != result && j != result){
//
//                    System.out.printf("%d %d %d \n", i, j ,result);
////                    if(a == b){
////                        paramMap.put("Rd_" + i, a);
////                    }else{
////                        paramMap.put("Rd_" + i, a);
////                    }
//
//                }
//            }
//
//        }
//
//
//
//
//
//
//        JSONObject jsonResult = new JSONObject();
//
//        paramMap.put("tishu", 50);
//        String s1 = CollectData.pressureResult(paramMap);
//        String resultHtml = s1.replaceAll("/mmpi", "http://140.143.237.60:8020/appraisal/lfxlcsxh.htm?ditch=").replaceAll("MMPI明尼苏达心理评估量表", "十项症状自评量表SCL90");
//
//
//        jsonResult.put("select", paramMap);
//        jsonResult.put("result", resultHtml);
//
//        String table = "ali_subject_record";
//        String type = "pressure_PSTR";
//
//        JsonData jsonData = new JsonData();
//        //使用表名+类型+参数生成MD5作为唯一ID
//        jsonData.setId(SecureUtil.md5(table + "-" + type + "-" + paramMap.toString()));
//
//        jsonData.setJsonData(jsonResult.toString());
//
//
//        //存储
//        System.out.println(jsonDataMapper.insert(jsonData));
//    }
//
//
////    @Test
//    public void contextLoads2() {
//
//        AlibbApplicationTests.test40ti(jsonDataMapper);
//
//    }
//
//
//    public static void main(String[] args) {
////        int min = 1;
////        int max = 5;
////        //模拟所有参数
////        //50道题  每道题5个选项（单选）Rd_1-5
////        int count = 0;
////        //穷举
////        for (int a = min; a <=max; a++){
////            for (int b = min; b <= max; b++) {
////                for (int c = min; c <= max; c++) {
////                    for (int d = min; d <= max; d++) {
////                        for (int e = min; e <= max; e++) {
////
////                            //参数
////                            JSONObject paramMap = new JSONObject();
////
////                            System.out.printf("%d %d %d %d %d \n", a, b, c, d, e);
////                            count++;
////                        }
////                    }
////                }
////            }
////        }
////
////        System.out.println(count);
//
//
//
//    }
//
//
//    /**
//     * 遍历抓取50题的所有答案0-200分
//     * @param jsonDataMapper
//     */
//    private static void test40ti(JsonDataMapper jsonDataMapper){
//
//
//        //1-50分成绩结果
//        //参数
//        JSONObject paramMap = new JSONObject();
//        JSONObject paramMapBefore = new JSONObject();
//        int score = 0;  //得分
//
//        for (int i = 0; i < 48; i++){
//            paramMapBefore.put("Rd_" + (i+1), 4);
//            score+=4;
//        }
//        paramMapBefore.put("Rd_" + (49), 4);
//        score+=4;
////        paramMapBefore.put("Rd_" + (50), 1);
////        score+=1;
//        //113分
//
//
//        for (int i = 49; i < 50; i++){
//
//            score = 200;
//            paramMap.clear();
//            paramMap.putAll(paramMapBefore);
//            paramMap.put("Rd_" + (i+1), 4);
//
//            for (int j = i+1; j < 50; j++){
//                paramMap.put("Rd_" + (j+1), 1);
//                score++;
//            }
//            paramMap.put("tishu", 50);
//            //请求
//            String s = CollectData.pressureResult(paramMap);
//
//
//
//            String table = "ali_subject_record_pressure_PSTR";
//            String type = score + "";
//
//            JsonData jsonData = new JsonData();
//
//
//            jsonData.setJsonDitch(table);
//            jsonData.setJsonType(type);
//
//            //使用表名+类型+参数生成MD5作为唯一ID
//            jsonData.setId(SecureUtil.md5(table + "-" + type + "-" + score));
//
//
//            JSONObject jsonResult = new JSONObject();
//            jsonResult.put("score", score);
//            jsonResult.put("result", s);
//
//
//            jsonData.setJsonData(jsonResult.toString());
//            jsonData.setCreateTime(new Date());
//
//            //存储
//            System.out.println(jsonDataMapper.insert(jsonData));
//        }
//
//
//
//
//
//    }
//
//
//
//
//
//    private static void exhaustivity(List<Integer> counts, int index, int count){
//
//
//            if(counts.size() >= 3){    //选项
//                count++;
//
//                if(count >= 4){ //题数
//                    return;
//                }
//
//
//
//                counts.add(index, index);
//                System.out.println(ArrayUtil.toString(counts));
//                exhaustivity(new ArrayList<>(), index, count);
//            }else{
//
//                if(counts.size() - index == 0){
//                    counts.add(index-index, index+1);
//                }else{
//                    counts.add(index-index, index);
//                }
//
//
//
//                counts.add(count-1, index+1);
//                exhaustivity(counts, index, count);
//            }
//    }
//
//
//
//}
