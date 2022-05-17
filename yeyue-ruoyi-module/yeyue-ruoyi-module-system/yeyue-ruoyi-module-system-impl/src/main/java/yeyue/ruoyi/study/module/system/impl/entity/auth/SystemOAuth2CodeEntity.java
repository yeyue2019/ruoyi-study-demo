package yeyue.ruoyi.study.module.system.impl.entity.auth;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.mybatis.core.entity.MyBatisEntity;

import java.time.LocalDateTime;

import static yeyue.ruoyi.study.module.system.impl.constants.SystemTableConstants.SYSTEM_AUTH_CODE;

/**
 * OAuth2 授权码
 *
 * @author yeyue
 * @date 2022-05-16 17:06:05
 */
@Data
@TableName(value = SYSTEM_AUTH_CODE, autoResultMap = true)
public class SystemOAuth2CodeEntity extends MyBatisEntity {

    /**
     * 编号
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 授权码
     */
    private String code;

    /**
     * 客户端编号
     */
    private String clientId;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 过期时间
     */
    private LocalDateTime expiresTime;

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 状态
     */
    private CommonStatusEnum status;
}
