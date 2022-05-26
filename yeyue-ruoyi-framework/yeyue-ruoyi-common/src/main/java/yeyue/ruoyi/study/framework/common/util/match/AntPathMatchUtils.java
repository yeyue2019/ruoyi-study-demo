package yeyue.ruoyi.study.framework.common.util.match;

import java.util.Arrays;

import org.springframework.util.AntPathMatcher;

/**
 * Ant路径匹配
 *
 * @author yeyue
 * @date 2022-04-20 15:12:56
 */
public abstract class AntPathMatchUtils {
    private static final AntPathMatcher MATCHER = new AntPathMatcher();

    /**
     * 单独匹配
     *
     * @param path 路径
     * @param pattern 匹配的格式
     * @return 结果
     */
    private static boolean match(String path, String pattern) {
        return MATCHER.match(pattern, path);
    }

    /**
     * 任一匹配
     *
     * @param path 路径
     * @param patterns 匹配的格式
     * @return 结果
     */
    public static boolean anyMatch(String path, String... patterns) {
        if (patterns == null) {
            return false;
        }
        return Arrays.stream(patterns).anyMatch(pattern -> match(path, pattern));
    }

    /**
     * 无一匹配
     *
     * @param path 路径
     * @param patterns 匹配的格式
     * @return 结果
     */
    public static boolean noneMatch(String path, String... patterns) {
        if (patterns == null) {
            return true;
        }
        return Arrays.stream(patterns).noneMatch(pattern -> match(path, pattern));
    }
}
