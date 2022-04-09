package yeyue.ruoyi.study.framework.common.pojo;

import io.swagger.annotations.*;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 分页查询参数
 *
 * @author yeyue
 * @date 2022-04-08 19:38:20
 */
@Data
@ApiModel
public class PageQueryParam implements Serializable {

    private static final Integer PAGE_NO = 1;
    private static final Integer PAGE_SIZE = 10;

    @ApiModelProperty(value = "页码，从 1 开始", required = true, example = "1")
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    private Integer pageNo = PAGE_NO;

    @ApiModelProperty(value = "每页条数，最大值为 100", required = true, example = "10")
    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    @Max(value = 100, message = "页码最大值为 100")
    private Integer pageSize = PAGE_SIZE;
}
