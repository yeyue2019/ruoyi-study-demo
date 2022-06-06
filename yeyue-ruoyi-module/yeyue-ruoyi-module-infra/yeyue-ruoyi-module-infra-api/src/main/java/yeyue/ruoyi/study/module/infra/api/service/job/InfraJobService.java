package yeyue.ruoyi.study.module.infra.api.service.job;

import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.module.infra.api.domain.job.InfraJobDomain;
import yeyue.ruoyi.study.module.infra.api.service.job.dto.InfraJobCreateReqDTO;
import yeyue.ruoyi.study.module.infra.api.service.job.dto.InfraJobPageReqDTO;
import yeyue.ruoyi.study.module.infra.api.service.job.dto.InfraJobStatusUpdateReqDTO;
import yeyue.ruoyi.study.module.infra.api.service.job.dto.InfraJobUpdateReqDTO;

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

    /**
     * 修改任务
     *
     * @param reqDTO 任务信息
     */
    void update(InfraJobUpdateReqDTO reqDTO);

    /**
     * 修改任务状态
     *
     * @param reqDTO 任务信息
     */
    void updateStatus(InfraJobStatusUpdateReqDTO reqDTO);

    /**
     * 触发一次任务
     *
     * @param id 任务Id
     */
    void trigger(Long id);

    /**
     * 删除任务
     *
     * @param id 任务Id
     */
    void delete(Long id);

    /**
     * 获取任务
     *
     * @param id 任务Id
     * @return 任务信息
     */
    InfraJobDomain get(Long id);

    /**
     * 任务列表
     *
     * @param reqDTO 查询条件
     * @return 任务列表
     */
    PageResult<InfraJobDomain> list(InfraJobPageReqDTO reqDTO);
}
