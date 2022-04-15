package yeyue.ruoyi.study.framework.common.pojo.pageable;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;

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
        return empty(0L);
    }

    public static <T> PageResult<T> empty(Long total) {
        return new PageResult<>(total);
    }

    // 防止字段被序列化 / 反序列化

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    @ApiModelProperty(hidden = true)
    public boolean isEmpty() {
        return CollectionUtils.isEmpty(this.list);
    }

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    @ApiModelProperty(hidden = true)
    public boolean isNotEmpty() {
        return !isEmpty();
    }
}
