package yeyue.ruoyi.study.module.system.impl.entity.department;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import yeyue.ruoyi.study.framework.mybatis.core.entity.MyBatisEntity;

import static yeyue.ruoyi.study.module.system.impl.constants.SystemTableConstants.SYSTEM_POST;

/**
 * 岗位
 *
 * @author yeyue
 * @date 2022-04-18 23:16:39
 */
@Data
@TableName(value = SYSTEM_POST, autoResultMap = true)
public class SystemPostEntity extends MyBatisEntity {

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
}
