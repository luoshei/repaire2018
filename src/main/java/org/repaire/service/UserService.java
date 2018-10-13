package org.repaire.service;

import org.repaire.mapper.UserAndUserInfoMapper;
import org.repaire.mapper.UserMapper;
import org.repaire.mapper.UserinfoMapper;
import org.repaire.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Luo on 2018/5/7.
 */
@Service
public class UserService implements IUserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Autowired
    private UserAndUserInfoMapper userAndUserInfoMapper;

    @Override
    public User createUser(User user){
        userMapper.insert(user);
        UserExample example = new UserExample();
        example.createCriteria().andOpenIdEqualTo(user.getOpenId());
        List<User> users = userMapper.selectByExample(example);
        Userinfo userinfo = new Userinfo();
        userinfo.setUid(users.get(0).getUid());
        userinfo.setOpenId(user.getOpenId());
        userinfo.setDormId("1栋320");
        userinfo.setPhoneNum("13000000000");
        userinfo.setRealCode("20160000000");
        userinfo.setRealName("张三");
        userinfoMapper.insert(userinfo);
        System.out.print("创建新用户成功！");
        return users.get(0);
    }
    @Override
    public User isUserExist(String openid){
        UserExample example = new UserExample();
        example.createCriteria().andOpenIdEqualTo(openid);
        List<User> users = userMapper.selectByExample(example);
        return users.isEmpty()?null:users.get(0);
    }

    /**
     * 查询所有的报修工
     * @return
     */
    @Override
    public BxResult findRepairmen() {
        /*UserExample ue = new UserExample();
        ue.createCriteria().andRoleEqualTo(3);
        List<User> users = userMapper.selectByExample(ue);*/
        return BxResult.ok(userAndUserInfoMapper.selectAllRepairmen(Role.REPAIRMAN));
    }

    /**
     *根据uid添加维修人员
     */
    @Override
    public BxResult addRepairan(int uid){
        User user = new User();
        user.setUid(uid);
        user.setRole(Role.REPAIRMAN);
        UserExample ue = new UserExample();
        ue.createCriteria().andUidEqualTo(uid);
        int result = userMapper.updateByExampleSelective(user,ue);
        //return BxResult.ok(userAndUserInfoMapper.selectAllRepairmen(Role.REPAIRMAN));
        return BxResult.ok(result);
    }

    /**
     * 查询所有的后勤人员
     * @return
     */
    @Override
    public BxResult findAllLogistics() {
        return BxResult.ok(userAndUserInfoMapper.selectAllRepairmen(Role.LOGISTICS));
    }

    public BxResult setRole(String openId,int role){
        User user = new User();
        user.setRole(role);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andOpenIdEqualTo(openId);
        userMapper.updateByExampleSelective(user,userExample);
        return BxResult.ok();
    }

    @Override
    public BxResult getUser(String openId) {
        UserExample ue = new UserExample();
        ue.createCriteria().andOpenIdEqualTo(openId);
        return BxResult.ok(userMapper.selectByExample(ue));
    }

}
