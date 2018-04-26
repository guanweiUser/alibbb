package com.springboot.alibb.web.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.util.StringUtils;
import com.springboot.alibb.service.IAliUserService;
import com.springboot.alibb.web.vo.AliUserVo;
import com.springboot.base.common.BaseController;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 用户
 *
 * @author GuanWeiMail@163.com
 */
@RestController
@RequestMapping("/user")
public class AliUserController extends BaseController {

    /**
     * 用户service
     */
    @Resource
    private IAliUserService iAliUserService;



    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAliUserList.json", produces = "application/json; charset=UTF-8", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getAliUserList(HttpServletRequest request, AliUserVo aliUserVo) {

        try {

//            //测评人IP
//            subjectRecordVo.setIp(this.getIpAddr(request));
//            //测评人浏览器信息
//            subjectRecordVo.setBrowserInfo(this.getBrowserInfo(request));

            //解析测评 获取结果 存储测评信息 返回测评结果
//            return aliUserVo.getCallback() + "(" + ).toString() + ")";
//            return JSONUtil.parseArray(iAliUserService.getAliUserList(aliUserVo)).toString();

            String callback = aliUserVo.getCallback();
            String result = iAliUserService.getAliUserList(aliUserVo).toString();
            //判断是否为jsonp调用
            if (StrUtil.isBlank(callback)) {
                return result;
            } else {
                return aliUserVo.getCallback() + "(" + result + ")";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "系统故障，请稍后再试!";
        }
    }

}