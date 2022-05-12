package yeyue.ruoyi.study.framework.mybatis.core.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.apache.ibatis.annotations.Param;
import yeyue.ruoyi.study.framework.common.pojo.pageable.*;
import yeyue.ruoyi.study.framework.mybatis.core.util.MyBatisUtils;

import java.io.Serializable;
import java.util.*;

/**
 * @author yeyue
 * @date 2022-04-09 22:40:59
 */
public interface MyBatisMapper<T> extends BaseMapper<T> {

    /**
     * 分页查询结果
     *
     * @param queryParam   分页参数
     * @param queryWrapper 查询条件
     * @return 结果
     */
    default PageResult<T> selectPage(PageParam queryParam, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        IPage<T> mpPage = MyBatisUtils.buildPage(queryParam);
        selectPage(mpPage, queryWrapper);
        return new PageResult<>(mpPage.getRecords(), mpPage.getTotal());
    }

    /**
     * 根据条件查询符合条件的结果
     *
     * @param field 字段名称
     * @param value 字段值
     * @return 结果
     */
    default T selectOne(String field, Object value) {
        return selectOne(new QueryWrapper<T>().eq(field, value));
    }

    /**
     * 根据条件查询符合条件的结果
     *
     * @param field 字段名称
     * @param value 字段值
     * @return 结果
     */
    default T selectOne(SFunction<T, ?> field, Object value) {
        return selectOne(new LambdaQueryWrapper<T>().eq(field, value));
    }

    /**
     * 查询结果集数量
     *
     * @return 数量
     */
    default Long selectCount() {
        return selectCount(new QueryWrapper<>());
    }

    /**
     * 查询符合条件的结果集数量
     *
     * @param field 字段名称
     * @param value 字段值
     * @return 数量
     */
    default Long selectCount(String field, Object value) {
        return selectCount(new QueryWrapper<T>().eq(field, value));
    }

    /**
     * 查询符合条件的结果集数量
     *
     * @param field 字段名称
     * @param value 字段值
     * @return 数量
     */
    default Long selectCount(SFunction<T, ?> field, Object value) {
        return selectCount(new LambdaQueryWrapper<T>().eq(field, value));
    }

    /**
     * 查询全部数据
     *
     * @return 结果
     */
    default List<T> selectList() {
        return selectList(new QueryWrapper<>());
    }

    /**
     * 根据单一条件查询数据
     *
     * @param field 字段名称
     * @param value 字段值
     * @return 结果
     */
    default List<T> selectList(String field, Object value) {
        return selectList(new QueryWrapper<T>().eq(field, value));
    }

    /**
     * 根据单一条件查询数据
     *
     * @param field 字段名称
     * @param value 字段值
     * @return 结果
     */
    default List<T> selectList(SFunction<T, ?> field, Object value) {
        return selectList(new LambdaQueryWrapper<T>().eq(field, value));
    }

    /**
     * 批量保存 批量保存有很多限制条件 参见类说明
     *
     * @param entities 保存的对象集合
     * @return 保存成功的数量
     */
    int insertBatchSomeColumn(@Param("list") Collection<T> entities);

    /**
     * 根据id批量保存
     *
     * @param entity 保存的实体数量
     * @param idList id集合
     * @return 保存成功的数量
     */
    int updateBatchColumnByIds(@Param(Constants.ENTITY) T entity, @Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
}
