package yeyue.ruoyi.study.module.system.impl.entity.dept;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import yeyue.ruoyi.study.framework.mybatis.core.entity.MyBatisEntity;

import static yeyue.ruoyi.study.module.system.impl.constants.SystemTableConstants.SYSTEM_POST;

/**
 * 岗位表 ： 目前仅用作用户身份的一个标识
 *
 * @author yeyue
 * @date 2022-04-18 23:16:39
 */
@Data
@TableName(value = SYSTEM_POST, autoResultMap = true)
public class SystemPostEntity extends MyBatisEntity {

    /**
     * 岗位序号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 岗位编码
     */
    private String code;

    /**
     * 岗位排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Integer status;
}
