package yeyue.ruoyi.study.framework.common.monitor.trace.util;

import yeyue.ruoyi.study.framework.common.constants.CommonConstants;
import yeyue.ruoyi.study.framework.common.servlet.util.ServletUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Http追踪工具
 *
 * @author yeyue
 * @date 2022-04-19 17:19:27
 */
public abstract class TracerHttpUtils {

    /**
     * 追踪Http请求
     * 需要保证request Body可重复读
     *
     * @param request 请求
     */
    public static void before(HttpServletRequest request) {
        TracerUtils.put(CommonConstants.TRACE_REQ_URL, ServletUtils.getUrl(request));
        TracerUtils.put(CommonConstants.TRACE_REQ_METHOD, ServletUtils.getMethod(request));
        TracerUtils.put(CommonConstants.TRACE_REQ_PARAM, ServletUtils.getParamMap(request));
        TracerUtils.put(CommonConstants.TRACE_REQ_HEADER, ServletUtils.getHeaderMap(request));
        TracerUtils.put(CommonConstants.TRACE_REQ_BODY, ServletUtils.getBodyString(request));
    }
}
