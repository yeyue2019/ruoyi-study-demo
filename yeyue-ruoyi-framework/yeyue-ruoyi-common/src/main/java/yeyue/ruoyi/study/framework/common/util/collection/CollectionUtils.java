package yeyue.ruoyi.study.framework.common.util.collection;

import org.springframework.util.Assert;
import yeyue.ruoyi.study.framework.common.pojo.PageResult;
import yeyue.ruoyi.study.framework.common.util.object.ObjectUtils;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * 集合操作工具类
 *
 * @author yeyue
 * @date 2022-04-08 20:50:35
 */
public abstract class CollectionUtils extends org.springframework.util.CollectionUtils {

    public static boolean isEmpty(PageResult<?> result) {
        return result == null || isEmpty(result.getList());
    }

    public static <T> Collection<T> singleton(T data) {
        return ObjectUtils.convert(data, Collections.emptyList(), Collections::singletonList);
    }

    public static boolean contain(Object source, Object... targets) {
        return Arrays.asList(targets).contains(source);
    }

    public static <T> List<T> filterList(Collection<T> from, Predicate<T> predicate) {
        if (isEmpty(from)) {
            return new ArrayList<>();
        }
        return from.stream().filter(predicate).collect(Collectors.toList());
    }

    public static <T, R> List<R> convertList(Collection<T> from, Function<T, R> function) {
        if (isEmpty(from)) {
            return new ArrayList<>();
        }
        return from.stream().map(function).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 集合分割处理
     *
     * @param from         源集合
     * @param childrenSize 子集合大小
     * @param <T>          对象类型
     * @return 结果
     */
    public static <T> List<List<T>> subList(List<T> from, int childrenSize) {
        Assert.isTrue(childrenSize > 0, "分割的大小必须大于0!");
        if (isEmpty(from)) {
            return new ArrayList<>();
        }
        int listSize = from.size();
        int n = listSize % childrenSize != 0 ? listSize / childrenSize + 1 : listSize / childrenSize;
        // 偏移量
        List<List<T>> result = new ArrayList<>(n);
        int offset = 0;
        for (int i = 0; i < n; i++) {
            List<T> value;
            if (i != n - 1) {
                value = from.subList(i * childrenSize, (i + 1) * childrenSize);
                offset++;
            } else {
                value = from.subList(childrenSize * offset, listSize);
            }
            result.add(value);
        }
        return result;
    }
}
