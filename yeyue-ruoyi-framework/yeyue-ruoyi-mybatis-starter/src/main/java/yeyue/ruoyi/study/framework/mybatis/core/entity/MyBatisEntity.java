package yeyue.ruoyi.study.framework.mybatis.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通用数据库实体
 *
 * @author yeyue
 * @date 2022-04-09 22:02:54
 */
@Data
@ApiModel
public abstract class MyBatisEntity implements Serializable {

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    protected LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建者")
    protected String creator;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    protected LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新者")
    protected LocalDateTime updater;

    @TableLogic(value = "false", delval = "true")
    @ApiModelProperty(value = "逻辑删除标识位")
    protected Boolean deleted;
}
