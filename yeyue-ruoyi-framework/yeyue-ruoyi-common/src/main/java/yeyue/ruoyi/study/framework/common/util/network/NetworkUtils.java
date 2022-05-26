package yeyue.ruoyi.study.framework.common.util.network;

import org.apache.commons.lang3.StringUtils;
import yeyue.ruoyi.study.framework.common.constants.StringConstants;

/**
 * 网络操作工具类 来源hu-tool
 *
 * @author yeyue
 * @date 2022-04-14 13:58:19
 */
public abstract class NetworkUtils {
    public static final String UNKNOWN_IP = "unknown";

    /**
     * 检测给定字符串是否为未知，多用于检测HTTP请求相关<br>
     *
     * @param checkString 被检测的字符串
     * @return 是否未知
     * @since 5.2.6
     */
    public static boolean isUnknown(String checkString) {
        return StringUtils.isBlank(checkString) || StringUtils.equalsIgnoreCase(UNKNOWN_IP, checkString);
    }

    /**
     * 从多级反向代理中获得第一个非unknown IP地址
     *
     * @param ip 获得的IP地址
     * @return 第一个非unknown IP地址
     * @since 4.4.1
     */
    public static String getMultistageReverseProxyIp(String ip) {
        // 多级反向代理检测
        if (ip != null && ip.indexOf(StringConstants.SPLIT_JOIN) > 0) {
            final String[] ips = ip.trim().split(StringConstants.SPLIT_JOIN);
            for (String subIp : ips) {
                if (!isUnknown(subIp)) {
                    ip = subIp;
                    break;
                }
            }
        }
        return ip;
    }
}
