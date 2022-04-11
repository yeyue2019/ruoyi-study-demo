package yeyue.ruoyi.study.framework.common.pojo;

import io.swagger.annotations.*;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import yeyue.ruoyi.study.framework.common.enums.QuerySortOrderEnum;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

import static yeyue.ruoyi.study.framework.common.constants.CommonConstants.*;

/**
 * 分页排序参数
 *
 * @author yeyue
 * @date 2022-04-08 19:41:52
 */
@Data
@ApiModel
public class SortedParam implements Serializable {


    @ApiModelProperty(value = "字段")
    @NotEmpty(message = "查询字段不可为空")
    private String field;

    @ApiModelProperty(value = "顺序")
    @NotEmpty(message = "查询顺序不可为空")
    @InEnum(value = QuerySortOrderEnum.class, message = "查询顺序可选值为%s")
    private String order;

    /**
     * 指定正序排序字段
     */
    public static SortedParam asc(String field) {
        SortedParam s = new SortedParam();
        s.field = field;
        s.order = ORDER_ASC;
        return s;
    }

    /**
     * 指定倒序排序字段
     */
    public static SortedParam desc(String field) {
        SortedParam s = new SortedParam();
        s.field = field;
        s.order = ORDER_DESC;
        return s;
    }

    public boolean ifAsc() {
        return StringUtils.equals(this.order, ORDER_ASC);
    }

    public boolean ifDesc() {
        return StringUtils.equals(this.order, ORDER_DESC);
    }
}
