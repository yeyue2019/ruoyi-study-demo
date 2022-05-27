package yeyue.ruoyi.study.module.system.impl.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.module.system.api.domain.user.SystemUserDomain;
import yeyue.ruoyi.study.module.system.api.service.user.SystemUserService;
import yeyue.ruoyi.study.module.system.api.service.user.dto.SystemUserCreateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.user.dto.SystemUserPageReq;
import yeyue.ruoyi.study.module.system.api.service.user.dto.SystemUserUpdatePwdReq;
import yeyue.ruoyi.study.module.system.impl.entity.user.SystemUserEntity;
import yeyue.ruoyi.study.module.system.impl.entity.user.convert.SystemUserConvert;
import yeyue.ruoyi.study.module.system.impl.framework.exception.SystemErrorCode;
import yeyue.ruoyi.study.module.system.impl.mapper.user.SystemUserMapper;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author yeyue
 * @date 2022-04-18 16:16:45
 */
@Slf4j
@Component
public class SystemUserServiceImpl implements SystemUserService {

    @Resource
    SystemUserMapper mapper;

    @Override
    public Long create(SystemUserCreateReqDTO create) {


        SystemUserEntity entity = SystemUserConvert.INSTANCE.toEntity(create);
        mapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void update(SystemUserDomain update) {
        SystemUserEntity entity = SystemUserConvert.INSTANCE.toEntity(update);
        update.setCreateTime(null);
        mapper.updateById(entity);
    }

    @Override
    public void updatePwd(Long id, SystemUserUpdatePwdReq req) {
        mapper.updatePwd(id, req.getNewPassword());
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        mapper.updateStatus(id, status);
    }

    @Override
    public Integer delete(Long id) {
        return mapper.deleteById(id);
    }

    @Override
    public SystemUserDomain getById(Long id) {
        return SystemUserConvert.INSTANCE.toDomain(mapper.selectById(id));
    }

    @Override
    public SystemUserDomain getByUsername(String username) {
        return SystemUserConvert.INSTANCE.toDomain(mapper.selectOne(SystemUserEntity::getUsername, username));
    }

    @Override
    public PageResult<SystemUserDomain> list(SystemUserPageReq req) {
        PageResult<SystemUserEntity> source = mapper.selectPage(req);
        return CollectionUtils.funcPage(source, SystemUserConvert.INSTANCE::toDomain);
    }

    private void checkUserOperate(Long id, String username, String mobile) {
        // id
        if (id != null) {
            if (mapper.selectById(id) != null) {
                throw new ServiceException(SystemErrorCode.USER_NOT_EXISTS);
            }
        }
        // username
        SystemUserEntity unameCompare = mapper.selectByUsername(username);
        if (unameCompare != null && (id == null || Objects.equals(id, unameCompare.getId()))) {
            throw new ServiceException(SystemErrorCode.USER_USERNAME_EXISTS);
        }
        // mobile
        SystemUserEntity mobileCompare = mapper.selectByMobile(mobile);
        if (mobileCompare != null && (id == null || Objects.equals(id, mobileCompare.getId()))) {
            throw new ServiceException(SystemErrorCode.USER_MOBILE_EXISTS);
        }

    }
}
