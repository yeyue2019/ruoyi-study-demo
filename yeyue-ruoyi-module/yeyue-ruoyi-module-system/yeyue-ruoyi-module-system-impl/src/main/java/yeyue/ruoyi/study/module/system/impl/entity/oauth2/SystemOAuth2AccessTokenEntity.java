package yeyue.ruoyi.study.module.system.impl.entity.oauth2;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import yeyue.ruoyi.study.framework.mybatis.core.entity.MyBatisEntity;
import yeyue.ruoyi.study.module.system.impl.constants.SystemTableConstants;

import java.time.LocalDateTime;
import java.util.List;

/**
 * OAuth2 访问令牌
 *
 * @author yeyue
 * @date 2022-05-16 17:21:24
 */
@Data
@TableName(value = SystemTableConstants.SYSTEM_OAUTH2_ACCESS_TOKEN, autoResultMap = true)
public class SystemOAuth2AccessTokenEntity extends MyBatisEntity {

    /**
     * 编号
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 授权类型
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> scopes;

    /**
     * 过期时间
     */
    private LocalDateTime expiresTime;
}
