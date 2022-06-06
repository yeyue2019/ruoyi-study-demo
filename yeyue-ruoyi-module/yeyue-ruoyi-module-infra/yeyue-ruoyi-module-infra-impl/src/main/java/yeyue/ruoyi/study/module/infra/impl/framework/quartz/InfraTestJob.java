package yeyue.ruoyi.study.module.infra.impl.framework.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.framework.quartz.core.handler.JobHandler;

/**
 * @author yeyue
 * @date 2022-06-06 17:30:26
 */
@Component("infraTestJob")
@Slf4j
public class InfraTestJob implements JobHandler {
    @Override
    public String execute(String param) throws Exception {
        return "执行成功";
    }
}
