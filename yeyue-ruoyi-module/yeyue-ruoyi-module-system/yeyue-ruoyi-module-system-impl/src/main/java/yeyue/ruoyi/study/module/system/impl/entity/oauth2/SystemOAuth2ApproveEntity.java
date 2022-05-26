package yeyue.ruoyi.study.module.system.impl.entity.oauth2;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.UserTypeEnum;
import yeyue.ruoyi.study.framework.mybatis.core.entity.MyBatisEntity;
import yeyue.ruoyi.study.module.system.impl.constants.SystemTableConstants;

import java.time.LocalDateTime;

/**
 * OAuth2 授权批准记录
 *
 * @author yeyue
 * @date 2022-05-26 16:17:25
 */
@Data
@TableName(value = SystemTableConstants.SYSTEM_OAUTH2_APPROVE, autoResultMap = true)
public class SystemOAuth2ApproveEntity extends MyBatisEntity {

    /**
     * 编号
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 用户类型
     * <p>
     * 枚举 {@link UserTypeEnum}
     */
    private Integer userType;

    /**
     * 客户端编号
     */
    private String clientId;

    /**
     * 授权范围
     */
    private String scope;

    /**
     * 过期时间
     */
    private LocalDateTime expiresTime;
}
