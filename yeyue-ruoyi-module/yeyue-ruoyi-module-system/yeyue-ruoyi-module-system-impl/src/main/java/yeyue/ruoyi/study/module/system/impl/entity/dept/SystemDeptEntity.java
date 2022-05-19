package yeyue.ruoyi.study.module.system.impl.entity.dept;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.mybatis.core.entity.MyBatisEntity;

import static yeyue.ruoyi.study.module.system.impl.constants.SystemTableConstants.SYSTEM_DEPT;

/**
 * 部门
 *
 * @author yeyue
 * @date 2022-04-18 23:12:40
 */
@Data
@TableName(value = SYSTEM_DEPT, autoResultMap = true)
public class SystemDeptEntity extends MyBatisEntity {

    /**
     * 部门编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父部门ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 负责人
     */
    private Long leaderUserId;

    /**
     * 电话区号
     */
    private String areaCode;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     */
    private Integer status;
}
