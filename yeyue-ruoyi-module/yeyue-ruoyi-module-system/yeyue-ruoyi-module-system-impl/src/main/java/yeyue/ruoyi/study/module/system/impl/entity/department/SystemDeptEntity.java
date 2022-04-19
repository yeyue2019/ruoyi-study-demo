package yeyue.ruoyi.study.module.system.impl.entity.department;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import yeyue.ruoyi.study.module.system.impl.entity.SystemEntity;

import static yeyue.ruoyi.study.module.system.impl.constants.SystemTableConstants.SYSTEM_DEPT;

/**
 * 部门
 *
 * @author yeyue
 * @date 2022-04-18 23:12:40
 */
@Data
@TableName(value = SYSTEM_DEPT, autoResultMap = true)
public class SystemDeptEntity extends SystemEntity {

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
}
