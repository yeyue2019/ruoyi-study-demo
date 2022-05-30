package yeyue.ruoyi.study.module.system.impl.service.user;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.exception.common.GlobalErrorCode;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.common.util.enums.EnumUtils;
import yeyue.ruoyi.study.framework.mybatis.core.query.MyBatisLambdaQueryWrapper;
import yeyue.ruoyi.study.module.system.api.domain.user.SystemUserDomain;
import yeyue.ruoyi.study.module.system.api.service.dept.SystemDeptService;
import yeyue.ruoyi.study.module.system.api.service.dept.SystemPostService;
import yeyue.ruoyi.study.module.system.api.service.permission.SystemPermissionService;
import yeyue.ruoyi.study.module.system.api.service.user.SystemUserService;
import yeyue.ruoyi.study.module.system.api.service.user.dto.*;
import yeyue.ruoyi.study.module.system.impl.entity.user.SystemUserEntity;
import yeyue.ruoyi.study.module.system.impl.entity.user.convert.SystemUserConvert;
import yeyue.ruoyi.study.module.system.impl.framework.exception.SystemErrorCode;
import yeyue.ruoyi.study.module.system.impl.mapper.user.SystemUserMapper;

import javax.annotation.Resource;
import java.util.Collections;
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
    @Resource
    SystemDeptService deptService;
    @Resource
    SystemPostService postService;
    @Resource
    SystemPermissionService permissionService;
    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    public Long create(SystemUserCreateReqDTO create) {
        SystemUserEntity unameCompare = mapper.selectByUsername(create.getUsername());
        if (unameCompare != null) {
            throw new ServiceException(SystemErrorCode.USER_USERNAME_EXISTS);
        }
        deptService.validate(Collections.singleton(create.getDeptId()));
        postService.validate(create.getPostIds());
        SystemUserEntity entity = SystemUserConvert.INSTANCE.toEntity(create);
        entity.setPassword(passwordEncoder.encode(create.getPassword()));
        mapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void update(Long id, SystemUserProfileUpdateReqDTO update) {
        if (mapper.selectById(id) == null) {
            throw new ServiceException(SystemErrorCode.USER_NOT_EXISTS);
        }
        SystemUserEntity unameCompare = mapper.selectByUsername(update.getUsername());
        if (unameCompare != null && !Objects.equals(id, unameCompare.getId())) {
            throw new ServiceException(SystemErrorCode.USER_USERNAME_EXISTS);
        }
        SystemUserEntity mobileCompare = mapper.selectByMobile(update.getMobile());
        if (mobileCompare != null && !Objects.equals(id, mobileCompare.getId())) {
            throw new ServiceException(SystemErrorCode.USER_MOBILE_EXISTS);
        }
        SystemUserEntity emailCompare = mapper.selectByEmail(update.getEmail());
        if (emailCompare != null && !Objects.equals(id, emailCompare.getId())) {
            throw new ServiceException(SystemErrorCode.USER_EMAIL_EXISTS);
        }
        SystemUserEntity entity = SystemUserConvert.INSTANCE.toEntity(update);
        mapper.updateById(entity);
    }

    @Override
    public void update(SystemUserDeptUpdateReqDTO update) {
        if (mapper.selectById(update.getId()) == null) {
            throw new ServiceException(SystemErrorCode.USER_NOT_EXISTS);
        }
        deptService.validate(Collections.singleton(update.getDeptId()));
        postService.validate(update.getPostIds());
        SystemUserEntity entity = SystemUserConvert.INSTANCE.toEntity(update);
        mapper.updateById(entity);
    }

    @Override
    public void update(Long id, SystemUserPasswordUpdateReqDTO req) {
        SystemUserEntity entity = mapper.selectById(id);
        if (entity == null) {
            throw new ServiceException(SystemErrorCode.USER_NOT_EXISTS);
        }
        if (!StringUtils.equals(entity.getPassword(), passwordEncoder.encode(req.getOldPassword()))) {
            throw new ServiceException(SystemErrorCode.USER_PASSWORD_FAILED);
        }
        SystemUserEntity user = new SystemUserEntity();
        user.setId(id);
        user.setPassword(passwordEncoder.encode(req.getNewPassword()));
        mapper.updateById(entity);
    }

    @Override
    public void replace(SystemUserPasswordReplaceReqDTO reqDTO) {
        if (mapper.selectById(reqDTO.getId()) == null) {
            throw new ServiceException(SystemErrorCode.USER_NOT_EXISTS);
        }
        SystemUserEntity entity = new SystemUserEntity();
        entity.setId(reqDTO.getId());
        entity.setPassword(passwordEncoder.encode(reqDTO.getPassword()));
        mapper.updateById(entity);
    }

    @Override
    public void update(SystemUserStatusUpdateReqDTO reqDTO) {
        if (mapper.selectById(reqDTO.getId()) == null) {
            throw new ServiceException(SystemErrorCode.USER_NOT_EXISTS);
        }
        SystemUserEntity entity = SystemUserConvert.INSTANCE.toEntity(reqDTO);
        mapper.updateById(entity);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        if (mapper.selectById(id) == null) {
            throw new ServiceException(SystemErrorCode.USER_NOT_EXISTS);
        }
        mapper.deleteById(id);
        permissionService.processUserDeleted(id);
    }

    @Override
    public SystemUserDomain get(SystemUserGetReqDTO reqDTO) {
        if (!reqDTO.validate()) {
            throw new ServiceException(GlobalErrorCode.BAD_REQUEST.getCode(), "用户获取字段不能为空");
        }
        SystemUserEntity entity;
        if (reqDTO.getId() != null) {
            entity = mapper.selectById(reqDTO.getId());
        } else if (StringUtils.isNotBlank(reqDTO.getUsername())) {
            entity = mapper.selectByUsername(reqDTO.getUsername());
        } else if (StringUtils.isNotBlank(reqDTO.getMobile())) {
            entity = mapper.selectByMobile(reqDTO.getMobile());
        } else {
            entity = mapper.selectByEmail(reqDTO.getEmail());
        }
        if (entity != null && reqDTO.getStatus() != null) {
            if (!Objects.equals(entity.getStatus(), reqDTO.getStatus())) {
                entity = null;
            }
        }
        return SystemUserConvert.INSTANCE.toDomain(entity);
    }

    @Override
    public PageResult<SystemUserDomain> list(SystemUserPageReqDTO req) {
        PageResult<SystemUserEntity> source = mapper.selectPage(req, new MyBatisLambdaQueryWrapper<SystemUserEntity>().
                likeLeft(SystemUserEntity::getUsername, req.getUsername())
                .eq(SystemUserEntity::getMobile, req.getMobile())
                .eq(SystemUserEntity::getDeptId, req.getDeptId())
                .eq(SystemUserEntity::getStatus, req.getStatus()));
        return CollectionUtils.funcPage(source, SystemUserConvert.INSTANCE::toDomain);
    }

    @Override
    public Long authenticate(String username, String password) {
        SystemUserDomain user = get(new SystemUserGetReqDTO().setUsername(username));
        if (user == null || (!StringUtils.equals(passwordEncoder.encode(password), user.getPassword()))) {
            throw new ServiceException(SystemErrorCode.AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (EnumUtils.notEquals(CommonStatusEnum.ENABLE, CommonStatusEnum::getStatus, user.getStatus())) {
            throw new ServiceException(SystemErrorCode.AUTH_LOGIN_USER_DISABLED);
        }
        return user.getId();
    }
}
