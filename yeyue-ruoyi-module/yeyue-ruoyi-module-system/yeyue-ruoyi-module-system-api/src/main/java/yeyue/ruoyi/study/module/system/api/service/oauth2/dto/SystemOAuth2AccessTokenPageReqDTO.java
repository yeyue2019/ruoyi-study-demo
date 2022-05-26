package yeyue.ruoyi.study.module.system.api.service.oauth2.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageParam;

/**
 * @author yeyue
 * @date 2022-05-26 14:27:06
 */
@Data
@ApiModel(description = "访问令牌列表")
public class SystemOAuth2AccessTokenPageReqDTO extends PageParam {
}
