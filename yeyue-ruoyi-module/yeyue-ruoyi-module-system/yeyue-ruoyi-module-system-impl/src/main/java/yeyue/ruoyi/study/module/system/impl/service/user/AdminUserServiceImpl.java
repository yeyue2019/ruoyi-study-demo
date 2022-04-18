package yeyue.ruoyi.study.module.system.impl.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.module.system.api.domain.user.AdminUserDomain;
import yeyue.ruoyi.study.module.system.api.service.user.AdminUserService;
import yeyue.ruoyi.study.module.system.api.service.user.dto.*;
import yeyue.ruoyi.study.module.system.impl.entity.user.AdminUserEntity;
import yeyue.ruoyi.study.module.system.impl.entity.user.convert.AdminUserConvert;
import yeyue.ruoyi.study.module.system.impl.mapper.user.AdminUserMapper;

import javax.annotation.Resource;

/**
 * @author yeyue
 * @date 2022-04-18 16:16:45
 */
@Slf4j
@Component
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    AdminUserMapper userMapper;

    @Override
    public Long create(AdminUserDomain create) {
        AdminUserEntity entity = AdminUserConvert.INSTANCE.convert(create);
        userMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void update(AdminUserDomain update) {
        AdminUserEntity entity = AdminUserConvert.INSTANCE.convert(update);
        update.setCreateTime(null);
        userMapper.updateById(entity);
    }

    @Override
    public void updatePasswd(Long id, AdminUserUpdatePasswdReqDTO req) {
        userMapper.updatePasswd(id, req.getNewPassword());
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
    public AdminUserDomain getById(Long id) {
        return AdminUserConvert.INSTANCE.convert(userMapper.selectById(id));
    }

    @Override
    public AdminUserDomain getByUsername(String username) {
        return AdminUserConvert.INSTANCE.convert(userMapper.selectOne(AdminUserEntity::getUsername, username));
    }

    @Override
    public PageResult<AdminUserDomain> list(AdminUserPageReqDTO req) {
        PageResult<AdminUserEntity> source = userMapper.selectPage(req);
        return CollectionUtils.convertPage(source, AdminUserConvert.INSTANCE::convert);
    }
}
