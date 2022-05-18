package yeyue.ruoyi.study.module.system.api.service.dept.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageParam;

/**
 * @author yeyue
 * @date 2022-05-17 21:32:31
 */
@Data
@ApiModel(description = "部门信息查询")
public class SystemDeptPageReqDTO extends PageParam {
}
