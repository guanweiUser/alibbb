package com.springboot.alibb.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.springboot.alibb.bean.*;
import com.springboot.alibb.mapper.AliUserMapper;
import com.springboot.alibb.service.IAliUserService;
import com.springboot.alibb.web.vo.AliUserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户
 */
@Service
class AliUserServiceImpl implements IAliUserService {

    /**
     * 日志
     * 推荐创建不可变静态类成员变量
     */
    private static final Log log = LogFactory.get();

    @Resource
    private AliUserMapper aliUserMapper;

    /**
     * 获取用户列表
     * @param aliUserVo
     * @return
     */
    @Override
    public JSONObject getAliUserList(AliUserVo aliUserVo) throws Exception {

        AliUserExample aliUserExample = new AliUserExample();
        //分页
        aliUserExample.setLimit(aliUserVo.getLimit());
        aliUserExample.setOffset(aliUserVo.getOffset());

        String userName = aliUserVo.getUserName();
        if (StrUtil.isNotBlank(userName)){
            aliUserExample.createCriteria().andUserNameLike(userName.trim());
        }

        JSONObject resultJson = new JSONObject();

        List<AliUser> aliUsers = aliUserMapper.selectByExample(aliUserExample);
        //获取总条数
        long total = aliUserMapper.countByExample(aliUserExample);
        resultJson.put("total", total);
        resultJson.put("limit", aliUserVo.getLimit());
        resultJson.put("offset", aliUserVo.getOffset());
        resultJson.put("data", aliUsers);


        return resultJson;
    }
}
