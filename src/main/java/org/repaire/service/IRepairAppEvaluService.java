package org.repaire.service;

import org.repaire.pojo.BxResult;
import org.repaire.pojo.Repaireevalu;

/**
 * Created by Luo on 2018/5/25.
 */
public interface IRepairAppEvaluService {

    /**
     * 创建新的评价
     */
    public BxResult createRepairAppEvalu(String repairAppId, Repaireevalu repaireevalu);
}
