package yeyue.ruoyi.study.framework.common.pojo.pageable;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.*;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import yeyue.ruoyi.study.framework.common.enums.SortOrderEnum;
import yeyue.ruoyi.study.framework.common.validation.annotation.InStringEnum;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

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
    @InStringEnum(value = SortOrderEnum.class, message = "查询顺序可选值为%s")
    private String order;

    /**
     * 指定正序排序字段
     */
    public static SortedParam asc(String field) {
        SortedParam s = new SortedParam();
        s.field = field;
        s.order = SortOrderEnum.ASC.getOrder();
        return s;
    }

    /**
     * 指定倒序排序字段
     */
    public static SortedParam desc(String field) {
        SortedParam s = new SortedParam();
        s.field = field;
        s.order = SortOrderEnum.DESC.getOrder();
        return s;
    }

    /**
     * 是否倒序
     *
     * @return 结果
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    @ApiModelProperty(hidden = true)
    public boolean isDesc() {
        return StringUtils.equals(this.order, SortOrderEnum.DESC.getOrder());
    }
}
