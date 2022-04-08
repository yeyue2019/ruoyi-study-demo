package yeyue.ruoyi.study.common.pojo;

import io.swagger.annotations.*;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * 分页查询结果
 *
 * @author yeyue
 * @date 2022-04-08 20:01:58
 */
@Data
@ApiModel
public class PageResult<T> implements Serializable {

    @ApiModelProperty(value = "数据", required = true)
    private List<T> list;

    @ApiModelProperty(value = "总量", required = true)
    private Long total;

    public PageResult() {
    }

    public PageResult(List<T> list, Long total) {
        this.list = list;
        this.total = total;
    }

    public PageResult(Long total) {
        this.list = new ArrayList<>();
        this.total = total;
    }

    public static <T> PageResult<T> empty() {
        return new PageResult<>(0L);
    }

    public static <T> PageResult<T> empty(Long total) {
        return new PageResult<>(total);
    }

}
