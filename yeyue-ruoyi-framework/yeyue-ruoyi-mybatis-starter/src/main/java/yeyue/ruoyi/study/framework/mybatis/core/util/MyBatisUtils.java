package yeyue.ruoyi.study.framework.mybatis.core.util;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageParam;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;

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
    public static <T> Page<T> buildPage(PageParam queryParam) {
        // 页码 + 数量
        Page<T> page = new Page<>(queryParam.getPageNo(), queryParam.getPageSize());
        // 排序字段
        if (CollectionUtils.isNotEmpty(queryParam.getSorts())) {
            page.addOrder(CollectionUtils.funcList(queryParam.getSorts(), param -> param.isDesc() ? OrderItem.desc(param.getField()) : OrderItem.asc(param.getField())));
        }
        return page;
    }

}
