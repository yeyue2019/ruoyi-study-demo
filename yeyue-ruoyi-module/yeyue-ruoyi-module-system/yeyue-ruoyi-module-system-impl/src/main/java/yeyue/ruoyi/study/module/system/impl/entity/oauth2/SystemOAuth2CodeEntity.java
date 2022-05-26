package yeyue.ruoyi.study.module.system.impl.entity.oauth2;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import yeyue.ruoyi.study.framework.mybatis.core.entity.MyBatisEntity;

import java.time.LocalDateTime;
import java.util.List;

import static yeyue.ruoyi.study.module.system.impl.constants.SystemTableConstants.SYSTEM_OAUTH2_CODE;

/**
 * OAuth2 授权码
 *
 * @author yeyue
 * @date 2022-05-16 17:06:05
 */
@Data
@TableName(value = SYSTEM_OAUTH2_CODE, autoResultMap = true)
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
     * 过期时间
     */
    private LocalDateTime expiresTime;

    /**
     * 客户端编号
     */
    private String clientId;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 授权范围
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> scopes;

    /**
     * 重定向地址
     */
    private String redirectUri;

    /**
     * 状态
     */
    private String state;
}
