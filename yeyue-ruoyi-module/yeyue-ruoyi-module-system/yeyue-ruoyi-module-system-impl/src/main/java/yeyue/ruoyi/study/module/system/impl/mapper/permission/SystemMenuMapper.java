package yeyue.ruoyi.study.module.system.impl.mapper.permission;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import yeyue.ruoyi.study.framework.mybatis.core.mapper.MyBatisMapper;
import yeyue.ruoyi.study.module.system.impl.entity.permission.SystemMenuEntity;

/**
 * @author yeyue
 * @date 2022-05-23 15:19:20
 */
public interface SystemMenuMapper extends MyBatisMapper<SystemMenuEntity> {

    default SystemMenuEntity selectByParentIdAndName(Long parentId, String name) {
        return selectOne(new LambdaQueryWrapper<SystemMenuEntity>()
                .eq(SystemMenuEntity::getParentId, parentId)
                .eq(SystemMenuEntity::getName, name));
    }
}
