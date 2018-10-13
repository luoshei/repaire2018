package org.repaire.service;

import org.repaire.pojo.BxResult;
import org.repaire.pojo.Repaireapp;
import org.repaire.pojo.User;

/**
 * Created by Luo on 2018/5/7.
 */
public interface IRepaireAppService {
    //创建新的报修工单
    public BxResult createRepaireApp(Repaireapp repaireapp);
    //根据报修工单状态返回相应的报修单
    public BxResult findRepaireAppByState(int state);
    //根据OPENID和报修工单状态返回相应的报修单
    public BxResult findRepaireAppByOpenIdAndState(String openid,int state);
    //根据报修工单ID返回报修单详情
    public BxResult findRepairAppById(String repairAppId);
    //根据OPENID返回报修工单
    public BxResult findRepairAppByOepnId(String repairOpenId);
    //设置订单状态
    public BxResult setRepairAppState(String repairAppId,int repairAppState);
    //设置维修人员
    public BxResult setRepairAppRepairman(String repairAppId,String repairOpenId);
    //根据报修申请工单Id修改报修工单数据
    public BxResult updateRepairApp(Repaireapp repaireapp);
    //2018年5月24日 22:27:22
    //撤销删除报修单
    public BxResult deleteRepairApp(String repaireapp);
    //查询维修人员被分配到但未处理的报修单集
    public BxResult findRepairAppByRepairmanAndState(String repairmen,int state);
    //获取长时间未处理的报修单
    public BxResult findSuspendingRepairApp();
    //获取所有报修单
    public BxResult findAllRepairApps();
    //获取维修人员所持有的所有报修单
    public BxResult findRepairAppByRepairman(String repairman);

    public BxResult findUnfinishedRepairApps(String openId);
}
