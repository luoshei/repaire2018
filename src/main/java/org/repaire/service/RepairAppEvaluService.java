package org.repaire.service;

import org.repaire.mapper.RepaireappMapper;
import org.repaire.mapper.RepaireevaluMapper;
import org.repaire.pojo.BxResult;
import org.repaire.pojo.Repaireapp;
import org.repaire.pojo.Repaireevalu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Luo on 2018/5/25.
 */
@Service
public class RepairAppEvaluService implements IRepairAppEvaluService {

    @Autowired
    private RepaireevaluMapper repaireevaluMapper;
    @Autowired
    private RepaireappMapper repaireappMapper;
    @Autowired
    private RepaireAppService repaireAppService;
    /**
     * 为报修单创建评价
     */
    @Override
    public BxResult createRepairAppEvalu(String repairAppId, Repaireevalu repaireevalu) {
        //新建评价
        String id = repairAppId + "evalue";
        int rank = repaireevalu.getRepairEvaluRank();
        String content = repaireevalu.getRepairEvaluContent();
        Date date = new Date();
        Repaireevalu temp = new Repaireevalu();
        temp.setRepairEvaluId(id);
        temp.setRepairEvaluRank(rank);
        temp.setRepairEvaluContent(content);
        temp.setRepairEvaluTime(date);
        repaireevaluMapper.insert(temp);

        //将评价记录的Id保存到对应的报修单中
        Repaireapp repaireapp = new Repaireapp();
        repaireapp.setRepairAppId(repairAppId);
        repaireapp.setRepairEvaluId(id);
        repaireappMapper.updateByPrimaryKeySelective(repaireapp);
        return repaireAppService.findRepairAppById(repairAppId);
    }
}
