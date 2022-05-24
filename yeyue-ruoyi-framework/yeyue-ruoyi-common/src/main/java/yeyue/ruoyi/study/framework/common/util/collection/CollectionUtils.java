package yeyue.ruoyi.study.framework.common.util.collection;

import org.springframework.util.Assert;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;

import java.lang.reflect.*;
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
    public static <T> T[] listToArray(List<T> list) {
        if (isEmpty(list)) {
            return empty();
        }
        return (T[]) Array.newInstance(getListClazz(list), list.size());
    }

    /* 带转换函数的集合数组转换 */

    public static <T, R> List<R> arrayToList(T[] array, Function<T, R> func) {
        return funcList(arrayToList(array), func);
    }

    public static <T, R> R[] listToArray(List<T> list, Function<T, R> func) {
        return funcArray(listToArray(list), func);
    }

    /* 集合类型互转 */

    public static <T, R> List<R> funcList(Collection<T> from, Function<T, R> func) {
        if (isEmpty(from)) {
            return new ArrayList<>();
        }
        return from.stream().map(func).filter(Objects::nonNull).collect(Collectors.toList());
    }


    public static <T, R> R[] funcArray(T[] from, Function<T, R> func) {
        if (isEmpty(from)) {
            return empty();
        }
        return listToArray(Arrays.stream(from).map(func).filter(Objects::nonNull).collect(Collectors.toList()));
    }

    public static <T, R> PageResult<R> funcPage(PageResult<T> from, Function<T, R> func) {
        return new PageResult<>(funcList(from.getList(), func), from.getTotal());
    }

    public static <T> Set<T> convertSet(List<T> list) {
        return new HashSet<>(list);
    }

    /**
     * 判断是否是子节点
     *
     * @param tree   树
     * @param func   树转换函数
     * @param parent 父节点
     * @param child  子节点
     * @param <K>    节点值
     * @param <V>    存放节点的对象
     * @return 结果
     */
    public static <K, V> boolean ifChild(final Map<K, List<V>> tree, Function<V, K> func, K parent, K child) {
        List<V> children = tree.get(parent);
        if (isEmpty(children)) {
            return false;
        }
        return children.stream().map(func).anyMatch(c -> Objects.equals(c, child));
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

    /**
     * 获取集合的泛型类型
     *
     * @param list 集合
     * @param <T>  集合泛型
     * @return 泛型类型
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getListClazz(List<T> list) {
        ParameterizedType parameterizedType = (ParameterizedType) list.getClass().getGenericSuperclass();//获取当前new对象的泛型的父类类型
        return (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }
}
