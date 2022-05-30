package yeyue.ruoyi.study.module.system.api.service.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author yeyue
 * @date 2022-05-30 13:31:22
 */
@Data
@ApiModel(description = "用户登录结果")
public class SystemAuthLoginRespDTO implements Serializable {

    @ApiModelProperty(value = "访问令牌", required = true, example = "happy")
    private String accessToken;

    @ApiModelProperty(value = "刷新令牌", required = true, example = "nice")
    private String refreshToken;

    @ApiModelProperty(value = "过期时间", required = true)
    private LocalDateTime expiresTime;
}
