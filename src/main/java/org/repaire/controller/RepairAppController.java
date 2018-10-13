package org.repaire.controller;

import org.repaire.pojo.BxResult;
import org.repaire.pojo.Repaireapp;
import org.repaire.pojo.Repaireevalu;
import org.repaire.service.IRepairAppEvaluService;
import org.repaire.service.RepairAppEvaluService;
import org.repaire.service.RepaireAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Luo on 2018/5/10.
 */
@Controller
public class RepairAppController {

    @Autowired
    public RepaireAppService ras;
    @Autowired
    private RepairAppEvaluService raes;

    @RequestMapping("/RepairApp/InsertRepairApp")
    @ResponseBody
    public BxResult insertRepairApp(Repaireapp ra){
        System.out.print(ra.getRepairAppDormId());
        return ras.createRepaireApp(ra);
    }

    @RequestMapping("/RepairApp/FindRepairAppByOpenId")
    @ResponseBody
    public BxResult findRepairAppByOepnId(Repaireapp ra){
        return ras.findRepairAppByOepnId(ra.getRepairOpenId());
    }


    @RequestMapping("/RepairApp/FindRepairAppByOpenIdAndState")
    @ResponseBody
    public BxResult findRepairAppByOpenIdAndState(Repaireapp ra){
        return ras.findRepaireAppByOpenIdAndState(ra.getRepairOpenId(),ra.getRepairAppState());
    }

    @RequestMapping("/RepairApp/FindRepairAppByState")
    @ResponseBody
    public BxResult findRepairAppByState(int RepairAppState){
        return ras.findRepaireAppByState(RepairAppState);
    }

    @RequestMapping("/RepairApp/FindRepairAppById")
    @ResponseBody
    public BxResult findRepairAppById(Repaireapp ra){
        return ras.findRepairAppById(ra.getRepairAppId());
    }

    //2018年5月16日 23:06:59
    @RequestMapping("/RepairApp/SetRepairAppState")
    @ResponseBody
    public BxResult setRepairAppState(String RepairAppId,int RepairAppState){
        return ras.setRepairAppState(RepairAppId,RepairAppState);
    }

    @RequestMapping("/RepairApp/SetRepairAppRepairman")
    @ResponseBody
    public BxResult setRepairAppRepairman(Repaireapp ra){
        return ras.setRepairAppRepairman(ra.getRepairAppId(),ra.getRepairman());
    }

    //学生修改报修单
    @RequestMapping("/RepairApp/UpdateRepairApp")
    @ResponseBody
    public BxResult updateRepairApp(String RepairAppId,
                                    String RepairAppDormId,
                                    String RepairAppStuId,
                                    String RepairAppStuName,
                                    String RepairAppStuTel,
                                    String RepairAppType,
                                    String  RepairAppReason,
                                    String RepairAppReasonImg
                                    ){
        Repaireapp repairapp = new Repaireapp();
        repairapp.setRepairAppId(RepairAppId);
        repairapp.setRepairAppDormId(RepairAppDormId);
        repairapp.setRepairAppStuId(RepairAppStuId);
        repairapp.setRepairAppStuName(RepairAppStuName);
        repairapp.setRepairAppStuTel(RepairAppStuTel);
        repairapp.setRepairAppType(RepairAppType);
        repairapp.setRepairAppReason(RepairAppReason);
        repairapp.setRepairAppReasonImg(RepairAppReasonImg);
        return ras.updateRepairApp(repairapp);
    }

    @RequestMapping("/RepairApp/DeleteRepairAppById")
    @ResponseBody
    public BxResult deleteRepairAppById(String RepairAppId){
        return ras.deleteRepairApp(RepairAppId);
    }

    /**
     * 2018-06-02 20:45:17
     * @param Repairman
     * @return
     */
    @RequestMapping("/RepairApp/FindRepairAppByRepairman")
    @ResponseBody
    public BxResult findRepairAppByRepairmanAndState( String Repairman){
        return ras.findRepairAppByRepairman(Repairman);
    }

    @RequestMapping("/RepairApp/FindRepairAppByRepairmanAndState")
    @ResponseBody
    public BxResult findRepairAppByRepairmanAndState(Repaireapp ra){
        return ras.findRepairAppByRepairmanAndState(ra.getRepairman(),ra.getRepairAppState());
    }

    @RequestMapping("/RepairApp/CreateRepairAppEvalu")
    @ResponseBody
    public BxResult createRepairAppEvalu(String RepairAppId, Repaireevalu repaireevalu){
        return raes.createRepairAppEvalu(RepairAppId,repaireevalu);
    }

    @RequestMapping("/RepairApp/FindSuspendingRepairApp")
    @ResponseBody
    public BxResult findSuspendingRepairApp(){
        return ras.findSuspendingRepairApp();
    }

    @RequestMapping("/RepairApp/FindAllRepairApps")
    @ResponseBody
    public BxResult findAllRepairApps(){
        return ras.findAllRepairApps();
    }

    @RequestMapping("/RepairApp/FindUnfinishedRepairApps")
    @ResponseBody
    public BxResult findUnfinishedRepairApps(String openId){
        return ras.findUnfinishedRepairApps(openId);
    }

}
