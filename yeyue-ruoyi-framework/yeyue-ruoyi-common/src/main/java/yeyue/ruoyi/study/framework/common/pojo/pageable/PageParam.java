package yeyue.ruoyi.study.framework.common.pojo.pageable;

import io.swagger.annotations.*;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

/**
 * 分页查询参数
 *
 * @author yeyue
 * @date 2022-04-08 19:38:20
 */
@Data
@ApiModel
public abstract class PageParam implements Serializable {

    public static final Integer PAGE_NO = 1;
    public static final Integer PAGE_SIZE = 10;

    // TODO: 2022/4/10 使用参数校验

    @ApiModelProperty(value = "页码，从 1 开始", required = true, example = "1")
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    protected Integer pageNo = PAGE_NO;

    @ApiModelProperty(value = "每页条数，最大值为 100", required = true, example = "10")
    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    @Max(value = 100, message = "页码最大值为 100")
    protected Integer pageSize = PAGE_SIZE;

    @ApiModelProperty(value = "排序参数")
    @Valid
    protected List<SortedParam> sorts;
}
