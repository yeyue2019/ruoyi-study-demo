package yeyue.ruoyi.study.module.system.impl.entity.auth;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.mybatis.core.entity.MyBatisEntity;

import static yeyue.ruoyi.study.module.system.impl.constants.SystemTableConstants.SYSTEM_AUTH_CLIENT;

/**
 * OAuth2.0 客户端
 *
 * @author yeyue
 * @date 2022-05-16 16:24:05
 */
@Data
@TableName(value = SYSTEM_AUTH_CLIENT, autoResultMap = true)
public class SystemOAuth2ClientEntity extends MyBatisEntity {

    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 客户端Id
     */
    private String clientId;

    /**
     * 密钥
     */
    private String secret;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;


    // 客户端配置

    /**
     * 授权码有效期
     */
    private Integer codeValiditySeconds;

    /**
     * 访问令牌有效期
     */
    private Integer accessTokenValiditySeconds;

    /**
     * 刷新令牌有效期
     */
    private Integer refreshTokenValiditySeconds;

    // TODO: 2022/5/17 授权的一些配置 暂时注释掉了

    /**
     * 状态
     */
    private CommonStatusEnum status;
}
