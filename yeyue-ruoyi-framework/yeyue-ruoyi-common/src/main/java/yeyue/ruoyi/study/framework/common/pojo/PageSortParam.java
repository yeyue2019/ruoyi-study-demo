package yeyue.ruoyi.study.framework.common.pojo;

import io.swagger.annotations.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页排序参数
 *
 * @author yeyue
 * @date 2022-04-08 19:41:52
 */
@Data
@ApiModel
public class PageSortParam implements Serializable {

    /**
     * 顺序 - 升序
     */
    public static final String ORDER_ASC = "asc";
    /**
     * 顺序 - 降序
     */
    public static final String ORDER_DESC = "desc";


    @ApiModelProperty(value = "字段")
    private String field;

    @ApiModelProperty(value = "顺序")
    private String order;

    /**
     * 指定正序排序字段
     */
    public static PageSortParam asc(String field) {
        PageSortParam s = new PageSortParam();
        s.field = field;
        s.order = ORDER_ASC;
        return s;
    }

    /**
     * 指定倒序排序字段
     */
    public static PageSortParam desc(String field) {
        PageSortParam s = new PageSortParam();
        s.field = field;
        s.order = ORDER_DESC;
        return s;
    }

}
