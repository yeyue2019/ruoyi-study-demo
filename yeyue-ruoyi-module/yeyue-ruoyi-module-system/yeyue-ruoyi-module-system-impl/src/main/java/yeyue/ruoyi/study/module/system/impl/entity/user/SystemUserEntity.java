package yeyue.ruoyi.study.module.system.impl.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import yeyue.ruoyi.study.framework.mybatis.core.entity.MyBatisEntity;
import yeyue.ruoyi.study.framework.mybatis.core.type.JsonLongSetTypeHandler;

import java.time.LocalDate;
import java.util.Set;

import static yeyue.ruoyi.study.module.system.impl.constants.SystemTableConstants.SYSTEM_USER;

/**
 * 系统用户实体
 *
 * @author yeyue
 * @date 2022-04-18 15:20:36
 */
@Data
@TableName(value = SYSTEM_USER, autoResultMap = true)
public class SystemUserEntity extends MyBatisEntity {

    /**
     * 系统Id
     */
    @TableId(type = IdType.ASSIGN_ID)
    protected Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 生日
     */
    private LocalDate birthDay;

    /**
     * 电话区号
     */
    private String areaCode;

    /**
     * 电话号码
     */
    private String mobile;

    /**
     * 邮件地址
     */
    private String email;

    /**
     * 用户自述
     */
    private String description;

    /**
     * 部门Id
     */
    private Long deptId;

    /**
     * 岗位编号Id组
     */
    @TableField(typeHandler = JsonLongSetTypeHandler.class)
    private Set<Long> postIds;

    /**
     * 状态
     */
    protected Integer status;
}
