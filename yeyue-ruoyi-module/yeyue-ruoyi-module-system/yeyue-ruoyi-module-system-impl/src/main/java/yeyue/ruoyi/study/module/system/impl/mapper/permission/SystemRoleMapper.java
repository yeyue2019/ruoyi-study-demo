package yeyue.ruoyi.study.module.system.impl.mapper.permission;

import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.framework.mybatis.core.mapper.MyBatisMapper;
import yeyue.ruoyi.study.framework.mybatis.core.query.MyBatisLambdaQueryWrapper;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemRolePageReqDTO;
import yeyue.ruoyi.study.module.system.impl.entity.permission.SystemRoleEntity;

/**
 * @author yeyue
 * @date 2022-05-24 10:42:15
 */
public interface SystemRoleMapper extends MyBatisMapper<SystemRoleEntity> {

    default SystemRoleEntity selectByCode(String code) {
        return selectOne(SystemRoleEntity::getCode, code);
    }

    default SystemRoleEntity selectByName(String name) {
        return selectOne(SystemRoleEntity::getName, name);
    }

    default PageResult<SystemRoleEntity> list(SystemRolePageReqDTO reqDTO) {
        return selectPage(reqDTO, new MyBatisLambdaQueryWrapper<SystemRoleEntity>()
                .eq(SystemRoleEntity::getStatus, reqDTO.getStatus())
                .eq(SystemRoleEntity::getCode, reqDTO.getCode())
                .like(SystemRoleEntity::getName, reqDTO.getName()));
    }
}
