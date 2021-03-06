package com.springboot.alibb.service.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.springboot.alibb.bean.DictionaryBig;
import com.springboot.alibb.bean.DictionaryBigKey;
import com.springboot.alibb.bean.SubjectRecord;
import com.springboot.alibb.bean.SubjectRecordExample;
import com.springboot.alibb.data.CollectData;
import com.springboot.alibb.mapper.DictionaryBigMapper;
import com.springboot.alibb.mapper.SubjectRecordMapper;
import com.springboot.alibb.service.ISubjectRecordService;
import com.springboot.alibb.web.vo.SubjectRecordVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static javax.swing.UIManager.get;

/**
 * 答题（测评）记录
 */
@Service
class SubjectRecordServiceImpl implements ISubjectRecordService {

    //推荐创建不可变静态类成员变量
    private static final Log log = LogFactory.get();

    @Resource
    private SubjectRecordMapper subjectRecordMapper;

    @Resource
    private DictionaryBigMapper dictionaryBigMapper;

    /**
     * 提交测评
     * @return
     */
    @Override
    public String addSubjectRecord(SubjectRecordVo subjectRecordVo) {


        //最终返回结果
        String resultHtml = "";

        //题型
        String subjectType = subjectRecordVo.getType();
        String s = null;

        //用户测评填报结果
        List<String> userResult = subjectRecordVo.getUserResult();

        if(StrUtil.isBlank(subjectType)){

            if(userResult != null && userResult.size() >= 51){
                subjectType = "geilixinli_90";
            }else {
                subjectType = "pressure_PSTR";
            }
        }

        //json提报以及结果信息
        JSONObject jsonResult = new JSONObject();
        //jsonp
        String callback = subjectRecordVo.getCallback();
        switch (subjectType){

            case "geilixinli_90" :  //给力心理90道题
                //获取结果
                String r_info = CollectData.geilixinliResult(userResult);
                s = r_info.replaceAll("<", "@xiaoyu").replaceAll(">", "@dayu").replaceAll("\r|\n", "");

                //存储结果信息
                jsonResult.put("select", userResult);
                jsonResult.put("result", r_info);
                resultHtml = callback + "({'html':'"+s+"'})";
                break;

            case "pressure_PSTR" :
                //参数
                JSONObject paramMap = new JSONObject();
                for (int i = 0; i < 50; i++){
                    String rd = userResult.get(i);
                    paramMap.put("Rd_" + (i+1), rd);
                }

                paramMap.put("tishu", 50);
                String s1 = CollectData.pressureResult(paramMap);

                resultHtml = s1.replaceAll("/mmpi", "http://140.143.237.60:8020/appraisal/lfxlcsxh.htm?ditch=" + subjectRecordVo.getDitch()).replaceAll("MMPI明尼苏达心理评估量表", "十项症状自评量表SCL90");
                s = resultHtml.replaceAll("<", "@xiaoyu").replaceAll(">", "@dayu").replaceAll("\r|\n", "").replaceAll("'", "@danyinhao");

                //存储结果信息
                jsonResult.put("select", paramMap);
                jsonResult.put("result", resultHtml);
                resultHtml = callback + "({'html':'"+s+"'})";
                break;
        }

        jsonResult.put("browserInfo", subjectRecordVo.getBrowserInfo());
        //存储提报以及结果信息
        subjectRecordVo.setResult(jsonResult.toString());

        subjectRecordVo.setId(SecureUtil.simpleUUID());
        //创建日期
        subjectRecordVo.setCreateTime(new Date());
        subjectRecordMapper.insert(subjectRecordVo);
        log.info("姓名-手机号：{}-{}，提交了测评信息!", subjectRecordVo.getName(), subjectRecordVo.getPhone());
        return resultHtml;
    }

    /**
     * 获取测评历史记录
     * @param subjectRecordVo
     * @return
     */
    @Override
    public JSONObject getSubjectRecordList(SubjectRecordVo subjectRecordVo) throws Exception {

        String ditch = subjectRecordVo.getDitch();
        String type = subjectRecordVo.getType();
        SubjectRecordExample subjectRecordExample = new SubjectRecordExample();
        //分页
        subjectRecordExample.setLimit(subjectRecordVo.getLimit());
        subjectRecordExample.setOffset(subjectRecordVo.getOffset());

        //自定义查询字段
        subjectRecordExample.setCustomField("id,name,phone,sex,age,create_time");

        subjectRecordExample.createCriteria().andDitchEqualTo(ditch).andTypeEqualTo(type);
        long total = subjectRecordMapper.countByExample(subjectRecordExample);

        subjectRecordExample.setOrderByClause("create_time DESC");
        JSONObject resultJson = new JSONObject();
        List<SubjectRecord> subjectRecords = subjectRecordMapper.selectByExample(subjectRecordExample);

        resultJson.put("data", subjectRecords);
        resultJson.put("total", total);
        resultJson.put("offset", subjectRecordVo.getOffset());
        resultJson.put("limit", subjectRecordVo.getLimit());


        return resultJson;
    }

    /**
     * 查看某人测评结果信息
     * @param subjectRecordVo
     * @return
     */
    @Override
    public SubjectRecord getSubjectRecordResultById(SubjectRecordVo subjectRecordVo) {

        SubjectRecordExample subjectRecordExample = new SubjectRecordExample();

        //自定义查询字段
        subjectRecordExample.setCustomField("id,name,phone,sex,age,create_time, type,JSON_UNQUOTE(JSON_EXTRACT(result, '$.result')) AS 'result'");


        subjectRecordExample.createCriteria().andIdEqualTo(subjectRecordVo.getId());

        List<SubjectRecord> subjectRecords = subjectRecordMapper.selectByExample(subjectRecordExample);
        if(subjectRecords != null && subjectRecords.size() >= 1){
            SubjectRecord subjectRecord = subjectRecords.get(0);

            String type = subjectRecord.getType();
            DictionaryBigKey dictionaryBigKey = new DictionaryBigKey();
            dictionaryBigKey.setDictName(type + "_before");
            dictionaryBigKey.setDictType("htmlAppend");
            DictionaryBig beforeHtml = dictionaryBigMapper.selectByPrimaryKey(dictionaryBigKey);

            dictionaryBigKey.setDictName(type + "_after");
            dictionaryBigKey.setDictType("htmlAppend");
            DictionaryBig afterHtml = dictionaryBigMapper.selectByPrimaryKey(dictionaryBigKey);
            if(beforeHtml != null && afterHtml != null){

                subjectRecord.setResult(beforeHtml.getDictValue() + subjectRecord.getResult() + afterHtml.getDictValue());
            }

            return subjectRecord;
        }

        return null;
    }
}
