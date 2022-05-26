package yeyue.ruoyi.study.framework.mybatis.core.query;

import java.util.Collection;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;

/**
 * 查询过滤条件
 *
 * @author yeyue
 * @date 2022-04-10 21:18:27
 */
public class MyBatisQueryWrapper<T> extends QueryWrapper<T> {

    @Override
    public MyBatisQueryWrapper<T> eq(String column, Object val) {
        return (MyBatisQueryWrapper<T>)super.eq(val != null, column, val);
    }

    public MyBatisQueryWrapper<T> like(String column, String val) {
        return (MyBatisQueryWrapper<T>)super.like(StringUtils.isNotEmpty(val), column, val);
    }

    @Override
    public MyBatisQueryWrapper<T> in(String column, Collection<?> coll) {
        return (MyBatisQueryWrapper<T>)super.in(CollectionUtils.isNotEmpty(coll), column, coll);
    }

    @Override
    public MyBatisQueryWrapper<T> in(String column, Object... values) {
        return (MyBatisQueryWrapper<T>)super.in(CollectionUtils.isNotNull(values), column, values);
    }

    @Override
    public MyBatisQueryWrapper<T> between(String column, Object val1, Object val2) {
        if (ObjectUtils.allNull(val1, val2)) {
            return this;
        }
        if (ObjectUtils.allNotNull(val1, val2)) {
            return (MyBatisQueryWrapper<T>)super.between(column, val1, val2);
        }
        if (val1 == null) {
            return (MyBatisQueryWrapper<T>)super.le(column, val2);
        }
        return (MyBatisQueryWrapper<T>)super.ge(column, val1);
    }
}
