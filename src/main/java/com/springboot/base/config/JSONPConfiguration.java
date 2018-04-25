package com.springboot.base.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Spring Boot支持跨域请求的JSONP数据
 * @program: alibbb
 *
 * @description:
 *
 * @author: Mr.Guan
 *
 * @Mail: GuanWeiMail@163.com
 *
 * @create: 2018-04-25 14:48
 **/
/*RestControllerAdvice的值指定拦截的包名*/
@ControllerAdvice(basePackages = {"com.springboot.alibb.web.controller"})
public class JSONPConfiguration extends AbstractJsonpResponseBodyAdvice {

    public JSONPConfiguration() {
        /*callback是url请求拦截的参数名，如果拦截成功会将返回数据传入函数执行回调函数*/
        super("callback", "jsonp");
    }

    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
                                           MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {

        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();

        //如果不存在callback这个请求参数，直接返回，不需要处理为jsonp
        if (ObjectUtils.isEmpty(servletRequest.getParameter("callback"))) {
            return;
        }
        //按设定的请求参数(JsonAdvice构造方法中的this.jsonpQueryParamNames = new String[]{"callback"};)，处理返回结果为jsonp格式
        String value = servletRequest.getParameter("callback");
        if (value != null) {
            MediaType contentTypeToUse = getContentType(contentType, request, response);
            response.getHeaders().setContentType(contentTypeToUse);
            bodyContainer.setJsonpFunction(value);
            return;
        }
    }

}
