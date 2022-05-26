package yeyue.ruoyi.study.framework.mybatis.core.query;

import java.util.Collection;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;

/**
 * 查询过滤条件
 *
 * @author yeyue
 * @date 2022-04-10 21:41:32
 */
public class MyBatisLambdaQueryWrapper<T> extends LambdaQueryWrapper<T> {

    @Override
    public MyBatisLambdaQueryWrapper<T> eq(SFunction<T, ?> column, Object val) {
        return (MyBatisLambdaQueryWrapper<T>)super.eq(val != null, column, val);
    }

    public MyBatisLambdaQueryWrapper<T> like(SFunction<T, ?> column, String val) {
        return (MyBatisLambdaQueryWrapper<T>)super.like(StringUtils.isNotEmpty(val), column, val);
    }

    @Override
    public MyBatisLambdaQueryWrapper<T> in(SFunction<T, ?> column, Collection<?> coll) {
        return (MyBatisLambdaQueryWrapper<T>)super.in(CollectionUtils.isNotEmpty(coll), column, coll);
    }

    @Override
    public MyBatisLambdaQueryWrapper<T> in(SFunction<T, ?> column, Object... values) {
        return (MyBatisLambdaQueryWrapper<T>)super.in(CollectionUtils.isNotNull(values), column, values);
    }

    @Override
    public MyBatisLambdaQueryWrapper<T> between(SFunction<T, ?> column, Object val1, Object val2) {
        if (ObjectUtils.allNull(val1, val2)) {
            return this;
        }
        if (ObjectUtils.allNotNull(val1, val2)) {
            return (MyBatisLambdaQueryWrapper<T>)super.between(column, val1, val2);
        }
        if (val1 == null) {
            return (MyBatisLambdaQueryWrapper<T>)super.le(column, val2);
        }
        return (MyBatisLambdaQueryWrapper<T>)super.ge(column, val1);
    }
}
