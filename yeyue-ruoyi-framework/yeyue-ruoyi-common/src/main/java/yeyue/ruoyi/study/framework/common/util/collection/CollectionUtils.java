package yeyue.ruoyi.study.framework.common.util.collection;

import org.springframework.util.Assert;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 集合操作工具类
 *
 * @author yeyue
 * @date 2022-04-08 20:50:35
 */
public abstract class CollectionUtils extends org.springframework.util.CollectionUtils {

    private static final Object[] EMPTY_ARRAY = new Object[0];

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static boolean isNull(Object... array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotNull(Object... array) {
        return !isNull(array);
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] empty() {
        return (T[]) EMPTY_ARRAY;
    }

    /* 集合和数组转化 */

    public static <T> List<T> arrayToList(T[] array) {
        if (isEmpty(array)) {
            return Collections.emptyList();
        }
        return Arrays.stream(array).collect(Collectors.toList());
    }

    @SuppressWarnings({"unchecked", "rawstype"})
    public static <T> T[] listToArray(List<T> list, Class<T> clazz) {
        if (isEmpty(list)) {
            return empty();
        }
        T[] result = (T[]) Array.newInstance(clazz, list.size());
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /* 带转换函数的集合数组转换 */

    public static <T, R> List<R> arrayToList(T[] array, Function<T, R> func) {
        return funcList(arrayToList(array), func);
    }

    public static <T, R> R[] listToArray(List<T> list, Class<R> clazz, Function<T, R> func) {
        return listToArray(funcList(list, func), clazz);
    }

    /* 集合类型互转 */

    public static <T, R> List<R> funcList(Collection<T> from, Function<T, R> func) {
        if (isEmpty(from)) {
            return Collections.emptyList();
        }
        return from.stream().map(func).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @SuppressWarnings({"unchecked", "rawstype"})
    public static <T, R> R[] funcArray(T[] from, Class<R> clazz, Function<T, R> func) {
        if (isEmpty(from)) {
            return empty();
        }
        R[] result = (R[]) Array.newInstance(clazz, from.length);
        for (int i = 0; i < result.length; i++) {
            result[i] = func.apply(from[i]);
        }
        return result;
    }

    public static <T, R> PageResult<R> funcPage(PageResult<T> from, Function<T, R> func) {
        return new PageResult<>(funcList(from.getList(), func), from.getTotal());
    }

    public static <T> Set<T> funcSet(Collection<T> source) {
        if (isEmpty(source)) {
            return Collections.emptySet();
        }
        return new HashSet<>(source);
    }

    public static <T, R> Set<R> funcSet(Collection<T> source, Function<T, R> func) {
        if (isEmpty(source)) {
            return Collections.emptySet();
        }
        return source.stream().map(func).collect(Collectors.toSet());
    }

    /**
     * 集合全包含
     *
     * @param source  被比较的集合
     * @param compare 比较的集合
     * @param <T>     集合类型
     * @return 结果
     */
    public static <T> boolean containsAll(Collection<T> source, Collection<T> compare) {
        if (isEmpty(compare)) {
            return true;
        }
        if (isEmpty(source)) {
            return false;
        }
        return new HashSet<>(source).containsAll(compare);
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
            return Collections.emptyList();
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
