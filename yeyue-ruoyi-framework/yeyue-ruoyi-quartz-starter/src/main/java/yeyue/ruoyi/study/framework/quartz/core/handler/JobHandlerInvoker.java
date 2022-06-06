package yeyue.ruoyi.study.framework.quartz.core.handler;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import yeyue.ruoyi.study.framework.quartz.core.enums.QuartzDatabaseKeyConstants;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author yeyue
 * @date 2022-06-01 10:45:24
 */
@Slf4j
@DisallowConcurrentExecution  // 不允许执行的实例
@PersistJobDataAfterExecution  // 设置参数传递
public class JobHandlerInvoker extends QuartzJobBean {

    @Resource
    ApplicationContext context;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // 1. 获取任务参数
        JobDataMap dataMap = context.getMergedJobDataMap();
        Long id = dataMap.getLong(QuartzDatabaseKeyConstants.JOB_ID);
        String handlerName = dataMap.getString(QuartzDatabaseKeyConstants.JOB_HANDLER_NAME);
        String handlerParam = dataMap.getString(QuartzDatabaseKeyConstants.JOB_HANDLER_PARAM);
        int refireCount = context.getRefireCount();
        int retryCount = (Integer) dataMap.getOrDefault(QuartzDatabaseKeyConstants.JOB_RETRY_COUNT, 0);
        int retryInterval = (Integer) dataMap.getOrDefault(QuartzDatabaseKeyConstants.JOB_RETRY_INTERVAL, 0);
        // 2. 执行任务
        LocalDateTime startTime = LocalDateTime.now();
        String result = null;
        Throwable exception = null;
        try {
            result = this.executeInternal(handlerName, handlerParam);
        } catch (Throwable throwable) {
            exception = throwable;
        }
        LocalDateTime endTime = LocalDateTime.now();
        // 3. 记录日志
        log.info("Quartz任务执行完成, 任务Id:{},处理器名称:{},任务参数:{},执行次数:{},开始时间:{},结束时间:{},任务结果:{}", id, handlerName, handlerParam, refireCount + 1, startTime, endTime, exception == null ? result : exception);
        // 4. 异常处理
        handleException(exception, refireCount, retryCount, retryInterval);
    }

    private String executeInternal(String handlerName, String handlerParam) throws Exception {
        JobHandler handler = context.getBean(handlerName, JobHandler.class);
        return handler.execute(handlerParam);
    }

    private void handleException(Throwable exception, int refireCount, int retryCount, int retryInterval) throws JobExecutionException {
        // 如果有异常，则进行重试
        if (exception == null) {
            return;
        }
        // 情况一：如果到达重试上限，则直接抛出异常即可
        if (refireCount >= retryCount) {
            throw new JobExecutionException(exception);
        }

        // 情况二：如果未到达重试上限，则 sleep 一定间隔时间，然后重试
        // 这里使用 sleep 来实现，主要还是希望实现比较简单。因为，同一时间，不会存在大量失败的 Job。
        if (retryInterval > 0) {
            try {
                Thread.sleep(retryInterval);
            } catch (InterruptedException ignored) {
            }
        }
        // 第二个参数，refireImmediately = true，表示立即重试
        throw new JobExecutionException(exception, true);
    }
}
