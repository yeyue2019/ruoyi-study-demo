package yeyue.ruoyi.study.module.infra.impl.service.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.module.infra.api.service.quartz.InfraJobService;
import yeyue.ruoyi.study.module.infra.api.service.quartz.dto.InfraJobCreateReqDTO;

/**
 * @author yeyue
 * @date 2022-06-02 15:11:27
 */
@Slf4j
@Component
public class InfraJobServiceImpl implements InfraJobService {

    @Override
    public Long create(InfraJobCreateReqDTO reqDTO) {
        return 0L;
    }
}
