package yeyue.ruoyi.study.framework.quartz.core.enums;

/**
 * @author yeyue
 * @date 2022-06-01 10:48:13
 */
public interface QuartzDatabaseKeyConstants {

    /**
     * 任务Id
     */
    String JOB_ID = "JOB_ID";

    /**
     * 任务处理器名称
     */
    String JOB_HANDLER_NAME = "JOB_HANDLER_NAME";

    /**
     * 任务处理器参数
     */
    String JOB_HANDLER_PARAM = "JOB_HANDLER_PARAM";

    /**
     * 最大重试次数
     */
    String JOB_RETRY_COUNT = "JOB_RETRY_COUNT";

    /**
     * 每次重试间隔
     */
    String JOB_RETRY_INTERVAL = "JOB_RETRY_INTERVAL";
}
