package yeyue.ruoyi.study.module.system.api.service.dept.dto;

import io.swagger.annotations.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageParam;

/**
 * @author yeyue
 * @date 2022-05-17 21:32:31
 */
@Data
@ApiModel(description = "部门信息查询")
public class SystemDeptPageReqDTO extends PageParam {

    @ApiModelProperty(value = "是否返回下级部门")
    private Boolean children;
}
