package yeyue.ruoyi.study.module.system.api.service.user.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageParam;

/**
 * 用户列表查询
 *
 * @author yeyue
 * @date 2022-04-18 16:10:55
 */
@Data
@ApiModel(description = "用户分页查询")
public class AdminUserPageReqDTO extends PageParam {
}
