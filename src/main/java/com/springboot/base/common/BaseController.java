package com.springboot.base.common;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller基类
 */
public class BaseController {


    /**
     * 获取真实IP
      * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取浏览器信息
      * @param request
     * @return
     */
    public String getBrowserInfo(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        String agent = request.getHeader("User-Agent").toLowerCase();
        sb.append("User-Agent：");
        sb.append(agent);
        //客户端电脑的名字
        String remoteHost = request.getRemoteHost();
        sb.append("；remoteHost：");
        sb.append(remoteHost);
        String remoteAddr = request.getRemoteAddr();
        sb.append("；remoteAddr：");
        sb.append(remoteAddr);
        return sb.toString();
    }

}
