package yeyue.ruoyi.study.module.system.impl.mapper.dept;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import yeyue.ruoyi.study.framework.mybatis.core.mapper.MyBatisMapper;
import yeyue.ruoyi.study.module.system.impl.entity.dept.SystemDeptEntity;

/**
 * @author yeyue
 * @date 2022-05-17 21:43:40
 */
public interface SystemDeptMapper extends MyBatisMapper<SystemDeptEntity> {

    default SystemDeptEntity selectByParentIdAndName(Long parentId, String name) {
        return selectOne(new LambdaQueryWrapper<SystemDeptEntity>().eq(SystemDeptEntity::getParentId, parentId)
            .eq(SystemDeptEntity::getName, name));
    }
}
