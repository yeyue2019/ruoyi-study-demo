package yeyue.ruoyi.study.module.system.impl.service.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.module.system.api.domain.user.SystemUserDomain;
import yeyue.ruoyi.study.module.system.api.service.user.SystemUserService;
import yeyue.ruoyi.study.module.system.api.service.user.dto.SystemUserPageReq;
import yeyue.ruoyi.study.module.system.api.service.user.dto.SystemUserUpdatePwdReq;
import yeyue.ruoyi.study.module.system.impl.entity.user.SystemUserEntity;
import yeyue.ruoyi.study.module.system.impl.entity.user.convert.SystemUserConvert;
import yeyue.ruoyi.study.module.system.impl.mapper.user.SystemUserMapper;

/**
 * @author yeyue
 * @date 2022-04-18 16:16:45
 */
@Slf4j
@Component
public class SystemUserServiceImpl implements SystemUserService {

    @Resource
    SystemUserMapper userMapper;

    @Override
    public Long create(SystemUserDomain create) {
        SystemUserEntity entity = SystemUserConvert.INSTANCE.convert(create);
        userMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void update(SystemUserDomain update) {
        SystemUserEntity entity = SystemUserConvert.INSTANCE.convert(update);
        update.setCreateTime(null);
        userMapper.updateById(entity);
    }

    @Override
    public void updatePwd(Long id, SystemUserUpdatePwdReq req) {
        userMapper.updatePwd(id, req.getNewPassword());
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        userMapper.updateStatus(id, status);
    }

    @Override
    public Integer delete(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public SystemUserDomain getById(Long id) {
        return SystemUserConvert.INSTANCE.convert(userMapper.selectById(id));
    }

    @Override
    public SystemUserDomain getByUsername(String username) {
        return SystemUserConvert.INSTANCE.convert(userMapper.selectOne(SystemUserEntity::getUsername, username));
    }

    @Override
    public PageResult<SystemUserDomain> list(SystemUserPageReq req) {
        PageResult<SystemUserEntity> source = userMapper.selectPage(req);
        return CollectionUtils.funcPage(source, SystemUserConvert.INSTANCE::convert);
    }
}
