package yeyue.ruoyi.study.framework.quartz.core.handler;

/**
 * 任务处理器
 *
 * @author yeyue
 * @date 2022-06-01 10:37:51
 */
public interface JobHandler {

    /**
     * 执行任务
     *
     * @param param 参数
     * @return 结果
     * @throws Exception 异常
     */
    String execute(String param) throws Exception;
}
