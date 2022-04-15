package yeyue.ruoyi.study.framework.common.monitor.trace.context;

/**
 * @author yeyue
 * @date 2022-04-15 12:01:52
 */
public interface YeyueTraceContext {

    /**
     * 获取TraceId
     *
     * @return 结果
     */
    String traceId();

    /**
     * 标记内容于当前上下文
     *
     * @param name  tag名称
     * @param value tag值
     */
    <T> void put(String name, T value);

    /**
     * 获取当前内容于上下文
     *
     * @param name  tag名称
     * @param clazz 内容的类型
     * @return 结果
     */
    <T> T get(String name, Class<T> clazz);
}
