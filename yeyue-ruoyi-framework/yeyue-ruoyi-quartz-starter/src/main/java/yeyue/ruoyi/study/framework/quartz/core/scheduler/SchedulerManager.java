package yeyue.ruoyi.study.framework.quartz.core.scheduler;

import org.quartz.*;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.exception.common.GlobalErrorCode;
import yeyue.ruoyi.study.framework.quartz.core.enums.QuartzDatabaseKeyConstants;
import yeyue.ruoyi.study.framework.quartz.core.handler.JobHandlerInvoker;

/**
 * @author yeyue
 * @date 2022-06-01 10:38:43
 */
public class SchedulerManager {

    private final Scheduler scheduler;

    public SchedulerManager(Scheduler scheduler) {
        this.scheduler = scheduler;
    }


    /**
     * 创建Quartz任务
     *
     * @param id             任务Id
     * @param handlerName    处理器名字
     * @param handlerParam   处理器参数
     * @param cronExpression CRON表达式
     * @param retryCount     重试次数
     * @param retryInterval  重试间隔
     */
    public void create(Long id, String handlerName, String handlerParam, String cronExpression, Integer retryCount, Integer retryInterval) {
        JobDetail jobDetail = JobBuilder.newJob(JobHandlerInvoker.class)
                .usingJobData(QuartzDatabaseKeyConstants.JOB_ID, id)
                .usingJobData(QuartzDatabaseKeyConstants.JOB_HANDLER_NAME, handlerName)
                .withIdentity(handlerName).build();
        Trigger trigger = this.buildTrigger(handlerName, handlerParam, cronExpression, retryCount, retryInterval);
        // 修改调度
        try {
            scheduler.rescheduleJob(new TriggerKey(handlerName), trigger);
        } catch (SchedulerException e) {
            throw new ServiceException(GlobalErrorCode.QUARTZ_JOB_EXCEPTION, e);
        }
    }

    /**
     * 更新Quartz任务
     *
     * @param handlerName    处理器名字
     * @param handlerParam   处理器参数
     * @param cronExpression CRON表达式
     * @param retryCount     重试次数
     * @param retryInterval  重试间隔
     */
    public void update(String handlerName, String handlerParam, String cronExpression, Integer retryCount, Integer retryInterval) {
        Trigger newTrigger = this.buildTrigger(handlerName, handlerParam, cronExpression, retryCount, retryInterval);
        try {
            scheduler.rescheduleJob(new TriggerKey(handlerName), newTrigger);
        } catch (SchedulerException e) {
            throw new ServiceException(GlobalErrorCode.QUARTZ_JOB_EXCEPTION, e);
        }
    }

    /**
     * 删除Quartz任务
     *
     * @param handlerName 处理器名字
     */
    public void delete(String handlerName) {
        try {
            scheduler.deleteJob(new JobKey(handlerName));
        } catch (SchedulerException e) {
            throw new ServiceException(GlobalErrorCode.QUARTZ_JOB_EXCEPTION, e);
        }
    }

    /**
     * 暂停Quartz任务
     *
     * @param handlerName 处理器名字
     */
    public void pause(String handlerName) {
        try {
            scheduler.pauseJob(new JobKey(handlerName));
        } catch (SchedulerException e) {
            throw new ServiceException(GlobalErrorCode.QUARTZ_JOB_EXCEPTION, e);
        }
    }

    /**
     * 启动Quartz的任务
     *
     * @param handlerName 处理器名字
     */
    public void resume(String handlerName) {
        try {
            scheduler.resumeJob(new JobKey(handlerName));
            scheduler.resumeTrigger(new TriggerKey(handlerName));
        } catch (SchedulerException e) {
            throw new ServiceException(GlobalErrorCode.QUARTZ_JOB_EXCEPTION, e);
        }
    }

    /**
     * 立即触发一次 Quartz 中的 Job
     *
     * @param id           编号
     * @param handlerName  处理器名字
     * @param handlerParam 处理器参数
     */
    public void trigger(Long id, String handlerName, String handlerParam) {
        JobDataMap data = new JobDataMap();
        data.put(QuartzDatabaseKeyConstants.JOB_ID, id);
        data.put(QuartzDatabaseKeyConstants.JOB_HANDLER_NAME, handlerName);
        data.put(QuartzDatabaseKeyConstants.JOB_HANDLER_PARAM, handlerName);
        // 触发任务
        try {
            scheduler.triggerJob(new JobKey(handlerName), data);
        } catch (SchedulerException e) {
            throw new ServiceException(GlobalErrorCode.QUARTZ_JOB_EXCEPTION, e);
        }
    }

    private Trigger buildTrigger(String handlerName, String handlerParam, String cronExpression,
                                 Integer retryCount, Integer retryInterval) {
        return TriggerBuilder.newTrigger()
                .withIdentity(handlerName)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .usingJobData(QuartzDatabaseKeyConstants.JOB_HANDLER_NAME, handlerName)
                .usingJobData(QuartzDatabaseKeyConstants.JOB_RETRY_COUNT, retryCount)
                .usingJobData(QuartzDatabaseKeyConstants.JOB_RETRY_INTERVAL, retryInterval)
                .build();
    }
}
