package org.repaire.service;

import org.repaire.mapper.RepaireappMapper;
import org.repaire.pojo.BxResult;
import org.repaire.pojo.Repaireapp;
import org.repaire.pojo.RepaireappExample;
import org.repaire.pojo.RepaireappUnion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.utils.JsonUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Luo on 2018/5/11.
 */
@Service
public class RepaireAppService implements IRepaireAppService{

    @Autowired
    public RepaireappMapper repaireappMapper;

    @Override
    public BxResult createRepaireApp(Repaireapp repaireapp) {


        RepaireappExample re = new RepaireappExample();
        re.createCriteria().andRepairOpenIdEqualTo(repaireapp.getRepairOpenId()).andRepairAppTimeGreaterThanOrEqualTo(new Date(new Date().getTime()-24*60*60*1000));
        int count = repaireappMapper.countByExample(re);
        if(count>=3)return BxResult.build(402,"24h内已满3条！");
        else {
            //去掉栋字
            StringBuffer dorm = new StringBuffer(repaireapp.getRepairAppDormId());
            dorm.deleteCharAt(dorm.indexOf("栋"));
            //生成报修工单ID
            String repairappId = new SimpleDateFormat("yyyyMMddHHmmss" + dorm).format(new Date());
            repaireapp.setRepairAppId(repairappId);//设置工单ID
            repaireapp.setRepairAppTime(new Date());//设置报修时间
            repaireapp.setRepairAppState(0);

            repaireappMapper.insert(repaireapp);
            return BxResult.ok();
        }
    }

    /**
     * 根据openid和报修工单状态返回报修单list
     * @param openid
     * @param state
     * @return
     */
    @Override
    public BxResult findRepaireAppByOpenIdAndState(String openid,int state) {
        /*RepaireappExample example = new RepaireappExample();
        example.createCriteria().andRepairOpenIdEqualTo(openid).andRepairAppStateEqualTo(state);
        List<Repaireapp> repaireapps = repaireappMapper.selectByExample(example);*/
        List<RepaireappUnion> repaireapps = repaireappMapper.selectRepaireAppByOpenIdAndStateUnion(openid,state);
        return BxResult.ok(repaireapps);
    }

    /**
     * 根据报修工单状态返回报修单list
     * @param state
     * @return
     */
    @Override
    public BxResult findRepaireAppByState(int state) {
        /*RepaireappExample example = new RepaireappExample();
        example.createCriteria().andRepairOpenIdEqualTo(openid).andRepairAppStateEqualTo(state);
        List<Repaireapp> repaireapps = repaireappMapper.selectByExample(example);*/
        List<RepaireappUnion> repaireapps = repaireappMapper.selectRepaireAppByStateUnion(state);
        return BxResult.ok(repaireapps);
    }

    /**
     * 通过报修工单的Id的查询
     * @param repairAppId
     * @return
     */
    @Override
    public BxResult findRepairAppById(String repairAppId) {
        /*RepaireappExample example = new RepaireappExample();
        example.createCriteria().andRepairAppIdEqualTo(repairAppId);
        Repaireapp repaireapp = repaireappMapper.selectByPrimaryKey(repairAppId);*/
        List<RepaireappUnion> repaireappUnion = repaireappMapper.selectByPrimaryKeyUnion(repairAppId);
        return BxResult.ok(repaireappUnion.isEmpty()?null:repaireappUnion.get(0));
    }

    /**
     * 根据用户的openid返回所有的报修工单
     * @param repairOpenId
     * @return
     */
    @Override
    public BxResult findRepairAppByOepnId(String repairOpenId) {
        /*RepaireappExample example = new RepaireappExample();
        example.createCriteria().andRepairOpenIdEqualTo(repairOpenId);
        List<Repaireapp> repaireapps = repaireappMapper.selectByExample(example);*/
        List<RepaireappUnion> repaireapps = repaireappMapper.selectRepairAppByOepnIdUnion(repairOpenId);
        return BxResult.ok(repaireapps);
    }

    /**
     * 设置报修单的状态
     * @param repairAppId
     * @param repairAppState
     * @return
     */
    @Override
    public BxResult setRepairAppState(String repairAppId, int repairAppState) {
        Repaireapp repaireapp = new Repaireapp();
        repaireapp.setRepairAppId(repairAppId);
        repaireapp.setRepairAppState(repairAppState);
        repaireappMapper.updateByPrimaryKeySelective(repaireapp);
        Repaireapp repaireappAfter= repaireappMapper.selectByPrimaryKey(repairAppId);
        return BxResult.ok(repaireappAfter);
    }

    /**
     * 根据报修工单Id分配维修人员
     * @param repairAppId
     * @param repairman
     * @return
     */
    @Override
    public BxResult setRepairAppRepairman(String repairAppId, String repairman) {
        Repaireapp repaireapp = new Repaireapp();
        repaireapp.setRepairAppId(repairAppId);
        repaireapp.setRepairman(repairman);
        repaireapp.setRepairAppState(1);
        repaireappMapper.updateByPrimaryKeySelective(repaireapp);
        List<RepaireappUnion> repaireappUnion = repaireappMapper.selectByPrimaryKeyUnion(repairAppId);
        return BxResult.ok(repaireappUnion.isEmpty()?null:repaireappUnion.get(0));
    }

    /**
     * 根据报修单id更新
     * @param repaireapp
     * @return
     */
    @Override
    public BxResult updateRepairApp(Repaireapp repaireapp) {
        repaireappMapper.updateByPrimaryKeySelective(repaireapp);
        List<RepaireappUnion> repaireappUnion = repaireappMapper.selectByPrimaryKeyUnion(repaireapp.getRepairAppId());
        return BxResult.ok(repaireappUnion.isEmpty()?null:repaireappUnion.get(0));
    }

    @Override
    public BxResult deleteRepairApp(String RepairAppId ) {
        repaireappMapper.deleteByPrimaryKey(RepairAppId);
        return BxResult.ok();
    }

    /**
     * 权限：维修人员
     * @param repairman
     * @param state
     * @return
     */
    @Override
    public BxResult findRepairAppByRepairmanAndState(String repairman, int state) {
        List<RepaireappUnion> repaireapps = repaireappMapper.selectRepaireAppByRepairmanAndStateUnion(repairman,state);
        return BxResult.ok(repaireapps);
    }

    @Override
    public BxResult findSuspendingRepairApp() {
        List<RepaireappUnion> repaireapps = repaireappMapper.selectSuspendingRepairAppUnion();
        return BxResult.ok(repaireapps);
    }

    @Override
    public BxResult findAllRepairApps() {
        List<Repaireapp> repaireapps = repaireappMapper.selectByExample(null);
        return BxResult.ok(repaireapps);
    }

    @Override
    public BxResult findRepairAppByRepairman(String repairman) {
        List<RepaireappUnion> repaireapps = repaireappMapper.selectRepaireAppByRepairmanUnion(repairman);
        return BxResult.ok(repaireapps);
    }

    @Override
    public BxResult findUnfinishedRepairApps(String openId) {
        RepaireappExample re = new RepaireappExample();
        re.createCriteria().andRepairOpenIdEqualTo(openId).andRepairAppStateLessThanOrEqualTo(2);
        List<Repaireapp> repaireapps = repaireappMapper.selectByExample(re);
        return BxResult.ok(repaireapps);
    }
}
