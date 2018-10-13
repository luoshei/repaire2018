package org.repaire.service;

import org.repaire.pojo.BxResult;
import org.repaire.pojo.User;

/**
 * Created by Luo on 2018/5/7.
 */
public interface IUserService {
    public User createUser(User user);
    public User isUserExist(String openid);
    public BxResult findRepairmen();
    public BxResult addRepairan(int uid);
    public BxResult findAllLogistics();
    public BxResult setRole(String openId,int role);
    public BxResult getUser(String openId);
}
