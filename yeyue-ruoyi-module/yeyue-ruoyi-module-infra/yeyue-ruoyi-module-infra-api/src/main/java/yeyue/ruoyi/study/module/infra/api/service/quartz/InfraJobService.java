package yeyue.ruoyi.study.module.infra.api.service.quartz;

import yeyue.ruoyi.study.module.infra.api.service.quartz.dto.InfraJobCreateReqDTO;

/**
 * @author yeyue
 * @date 2022-06-02 14:27:59
 */
public interface InfraJobService {

    /**
     * 创建任务
     *
     * @param reqDTO 任务信息
     * @return 结果
     */
    Long create(InfraJobCreateReqDTO reqDTO);
}
