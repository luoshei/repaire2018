package org.repaire.controller;

import com.sun.jna.platform.win32.Netapi32Util;
import org.repaire.pojo.BxResult;
import org.repaire.pojo.Repaireapp;
import org.repaire.pojo.Userinfo;
import org.repaire.service.IUserInfoService;
import org.repaire.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Luo on 2018/5/16.
 */
@Controller
public class UserInfoController {

    @Autowired
    private UserInfoService uifs;

    @RequestMapping("/UserInfo/FindUserInfoByOpenId")
    @ResponseBody
    public BxResult findUserInfoByOpenId(@RequestParam("openId") String openId){
        return uifs.findUserinfoByOpenId(openId);
    }
    @RequestMapping("/UserInfo/UpdateUserinfo")
    @ResponseBody
    public BxResult updateUserinfo(Userinfo userinfo){
        return uifs.updateUserinfo(userinfo);
    }

}
