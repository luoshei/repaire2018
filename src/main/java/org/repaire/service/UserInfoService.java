package org.repaire.service;

import org.repaire.mapper.UserinfoMapper;
import org.repaire.pojo.BxResult;
import org.repaire.pojo.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Luo on 2018/5/16.
 */
@Service
public class UserInfoService implements IUserInfoService {

    @Autowired
    UserinfoMapper userinfoMapper;

    /**
     * 根据openid返回详细信息
     * @param openid
     * @return
     *
     * 录入文档
     */
    @Override
    public BxResult findUserinfoByOpenId(String openid) {
        Userinfo userinfo = userinfoMapper.selectByPrimaryKey(openid);
        return BxResult.ok(userinfo);
    }

    /**
     * 更新个人信息
     * @param userInfo
     * @return
     */
    @Override
    public BxResult updateUserinfo(Userinfo userInfo) {
        userinfoMapper.updateByPrimaryKeySelective(userInfo);
        return BxResult.ok(userinfoMapper.selectByPrimaryKey(userInfo.getOpenId()));
    }


}
