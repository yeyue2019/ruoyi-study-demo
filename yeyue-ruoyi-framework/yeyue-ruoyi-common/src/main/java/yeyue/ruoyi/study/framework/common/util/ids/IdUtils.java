package yeyue.ruoyi.study.framework.common.util.ids;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static yeyue.ruoyi.study.framework.common.constants.StringConstants.REGEX_JOIN;

/**
 * Id生成工具类
 *
 * @author yeyue
 * @date 2022-04-21 14:39:10
 */
public abstract class IdUtils {

    /**
     * 获取UUID
     *
     * @param isSimple 是否简化（去掉-）
     * @return 结果
     */
    public static String uuid(boolean isSimple) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        UUID uuid = new UUID(random.nextInt(), random.nextInt());
        if (isSimple) {
            return RegExUtils.replaceAll(uuid.toString(), REGEX_JOIN, StringUtils.EMPTY);
        }
        return uuid.toString();
    }

    /**
     * 随机字符串
     *
     * @param count    字符串长度
     * @param isSimple 是否简化（不含符号）
     * @return 结果
     */
    public static String random(int count, boolean isSimple) {
        if (isSimple) {
            return RandomStringUtils.randomAlphanumeric(count);
        } else {
            return RandomStringUtils.randomAscii(count);
        }
    }

}
