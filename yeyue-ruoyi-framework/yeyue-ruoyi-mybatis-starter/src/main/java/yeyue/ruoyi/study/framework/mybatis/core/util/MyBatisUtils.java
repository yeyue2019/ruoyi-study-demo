package yeyue.ruoyi.study.framework.mybatis.core.util;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import yeyue.ruoyi.study.framework.common.pojo.*;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;

import java.util.Collection;

/**
 * mybatis操作辅助工具类
 *
 * @author yeyue
 * @date 2022-04-09 22:43:12
 */
public abstract class MyBatisUtils {

    /**
     * 构建分页查询参数
     */
    public static <T> Page<T> buildPage(PageQueryParam queryParam) {
        return buildPage(queryParam, null);
    }

    /**
     * 构建分页查询参数
     */
    public static <T> Page<T> buildPage(PageQueryParam queryParam, Collection<PageSortParam> sortParams) {
        // 页码 + 数量
        Page<T> page = new Page<>(queryParam.getPageNo(), queryParam.getPageSize());
        // 排序字段
        if (CollectionUtils.isNotEmpty(sortParams)) {
            page.addOrder(CollectionUtils.convertList(sortParams, param -> param.ifDesc() ? OrderItem.desc(param.getField()) : OrderItem.asc(param.getField())));
        }
        return page;
    }

}
