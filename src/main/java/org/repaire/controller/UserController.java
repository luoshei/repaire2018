package org.repaire.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.jna.platform.win32.Netapi32Util;
import org.apache.log4j.Logger;
import org.repaire.pojo.BxResult;
import org.repaire.pojo.Role;
import org.repaire.pojo.User;
import org.repaire.pojo.Userinfo;
import org.repaire.service.UserInfoService;
import org.repaire.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.utils.WXAppletUserInfo;

import java.net.URLDecoder;
import java.util.Date;

/**
 * Created by Luo on 2018/5/7.
 */
@Controller
public class UserController {
    private static final String LOGISTICS = "GDPBX002";
    private static final String REPAIRMAN = "GDPBX003";
    private static final String TEACHER = "GDPBX004";
    private static int role = 1;
    private static Logger log = Logger.getLogger(UserController.class);
    @Autowired
    public UserService userService;
    @Autowired
    public UserInfoService userInfoService;

    @RequestMapping("/wxlogin")
    @ResponseBody
    public BxResult wxlogin(@RequestParam("openid") String openid,
                            @RequestParam("nickname") String nickname,
                            @RequestParam("checkcode") String checkcode) throws Exception {
        System.out.println("接收到微信小程序的 code:" + openid + "-" +
                nickname + "-" +
                checkcode + "-"
        );
        //判断openId是否已经在数据库中
        User user = userService.isUserExist(openid);
        if (user != null) {//存在
            return BxResult.ok(user);
        } else {
            //如果有checkcode首先验证checkcode
            int trole = 1;
            if (checkcode!=null && !checkcode.equals("") && !checkcode.equals("undefined")) {
                if (checkcode.equals(LOGISTICS)) {
                    trole = 2;
                } else if (checkcode.equals(REPAIRMAN)) {
                    trole = 3;
                } else if (checkcode.equals(TEACHER)) {
                    trole = 4;
                } else {
                    log.error("效验码不正确");
                    return BxResult.build(401, "效验码不正确");
                }
            }
            log.info(openid);
            User userNew = new User();
            userNew.setOpenId(openid);
            userNew.setNickName(nickname);
            userNew.setRole(trole);
            userNew.setCreationDate(new Date());
            return BxResult.ok(userService.createUser(userNew));
        }
    }
    /**
     * 判断是否已经注册本小程序
     *
     * @param openid
     * @return
     * @throws Exception
     */
    @RequestMapping("/wxIsRegi")
    @ResponseBody
    public BxResult wxIsRegi(@RequestParam("openid") String openid) throws Exception {
        //判断openId是否已经在数据库中
        User user = userService.isUserExist(openid);
        if (user != null) {//存在
            return BxResult.build(701, "已经注册过，可直接登录");
        } else {//不存在,
            return BxResult.build(702, "未注册过");
        }
    }
    @RequestMapping("/wxGetOpenId")
    @ResponseBody
    public BxResult wxGetOpenId(@RequestParam("code") String code) throws Exception {
        //获取openId
        JSONObject jsonObject = WXAppletUserInfo.getSessionKeyOropenid(code);
        return BxResult.ok(jsonObject);
    }

    /**
     * 查询所有的维修工
     * @return
     * @throws Exception
     */

    @RequestMapping("/FindAllRepairmen")
    @ResponseBody
    public BxResult findAllRepairmen() throws Exception {
        return  userService.findRepairmen();
    }

    /**
     * 新增维修工
     * @return
     * @throws Exception
     */

    @RequestMapping("/AddRepairmen")
    @ResponseBody
    public BxResult addRepairmen(int uid) throws Exception {
        return  userService.addRepairan(uid);
    }
    /**
     * 查询所有的后勤人员
     * @return
     * @throws Exception
     */
    @RequestMapping("/FindAllLogistics")
    @ResponseBody
    public BxResult findAllLogistics() throws Exception {
        return  userService.findAllLogistics();
    }

    /**
     * 修改用户为-学生
     */
    @RequestMapping("/SetRole2Stu")
    @ResponseBody
    public BxResult setRole2Stu(String openId) throws Exception {
        return  userService.setRole(openId, Role.STUDENT);
    }

    /**
     *
     * 修改用户为-后勤人员
     */
    @RequestMapping("/SetRole2Log")
    @ResponseBody
    public BxResult setRole2Log(String openId) throws Exception {
        return  userService.setRole(openId, Role.LOGISTICS);
    }

    /**
     *
     * 修改用户为-维修人员
     */
    @RequestMapping("/SetRole2Rep")
    @ResponseBody
    public BxResult setRole2Rep(String openId) throws Exception {
        return  userService.setRole(openId, Role.REPAIRMAN);
    }
    /**
     *
     * 修改用户为-辅导员
     */
    @RequestMapping("/SetRole2Tea")
    @ResponseBody
    public BxResult setRole2Tea(String openId) throws Exception {
        return  userService.setRole(openId, Role.TEACHER);
    }
    /**
     *
     * 修改用户为角色
     */
    @RequestMapping("/SetRole")
    @ResponseBody
    public BxResult setRole(String openId,int role) throws Exception {
        return  userService.setRole(openId, role);
    }

    @RequestMapping("/GetUser")
    @ResponseBody
    public BxResult getRole(String openId){
        return userService.getUser(openId);
    }
}
