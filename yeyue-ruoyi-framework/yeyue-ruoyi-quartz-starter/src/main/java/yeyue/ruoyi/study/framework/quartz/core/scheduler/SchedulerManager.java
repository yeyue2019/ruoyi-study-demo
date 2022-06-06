package yeyue.ruoyi.study.framework.quartz.core.scheduler;

import org.quartz.*;
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
    public void create(Long id, String handlerName, String handlerParam, String cronExpression, Integer retryCount, Integer retryInterval) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(JobHandlerInvoker.class)
                .usingJobData(QuartzDatabaseKeyConstants.JOB_ID, id)
                .usingJobData(QuartzDatabaseKeyConstants.JOB_HANDLER_NAME, handlerName)
                .withIdentity(handlerName).build();
        Trigger trigger = this.buildTrigger(handlerName, handlerParam, cronExpression, retryCount, retryInterval);
        // 修改调度
        scheduler.rescheduleJob(new TriggerKey(handlerName), trigger);
    }

    /**
     * 更新Quartz任务
     *
     * @param handlerName    处理器名字
     * @param handlerParam   处理器参数
     * @param cronExpression CRON表达式
     * @param retryCount     重试次数
     * @param retryInterval  重试间隔
     * @throws SchedulerException 更新异常
     */
    public void update(String handlerName, String handlerParam, String cronExpression, Integer retryCount, Integer retryInterval) throws SchedulerException {
        Trigger newTrigger = this.buildTrigger(handlerName, handlerParam, cronExpression, retryCount, retryInterval);
        scheduler.rescheduleJob(new TriggerKey(handlerName), newTrigger);
    }

    /**
     * 删除Quartz任务
     *
     * @param handlerName 处理器名字
     * @throws SchedulerException 删除异常
     */
    public void delete(String handlerName) throws SchedulerException {
        scheduler.deleteJob(new JobKey(handlerName));
    }

    /**
     * 暂停Quartz任务
     *
     * @param handlerName 处理器名字
     * @throws SchedulerException 暂停异常
     */
    public void pause(String handlerName) throws SchedulerException {
        scheduler.pauseJob(new JobKey(handlerName));
    }

    /**
     * 启动Quartz的任务
     *
     * @param handlerName 处理器名字
     * @throws SchedulerException 启动异常
     */
    public void resume(String handlerName) throws SchedulerException {
        scheduler.resumeJob(new JobKey(handlerName));
        scheduler.resumeTrigger(new TriggerKey(handlerName));
    }

    /**
     * 立即触发一次 Quartz 中的 Job
     *
     * @param id           编号
     * @param handlerName  处理器名字
     * @param handlerParam 处理器参数
     * @throws SchedulerException 触发异常
     */
    public void trigger(Long id, String handlerName, String handlerParam) throws SchedulerException {
        JobDataMap data = new JobDataMap();
        data.put(QuartzDatabaseKeyConstants.JOB_ID, id);
        data.put(QuartzDatabaseKeyConstants.JOB_HANDLER_NAME, handlerName);
        data.put(QuartzDatabaseKeyConstants.JOB_HANDLER_PARAM, handlerName);
        // 触发任务
        scheduler.triggerJob(new JobKey(handlerName), data);
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
