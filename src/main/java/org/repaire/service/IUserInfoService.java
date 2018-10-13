package org.repaire.service;

import org.repaire.pojo.BxResult;
import org.repaire.pojo.Userinfo;

/**
 * Created by Luo on 2018/5/16.
 */
public interface IUserInfoService {
    public BxResult findUserinfoByOpenId(String openid);
    public BxResult updateUserinfo(Userinfo userInfo);
}
