package yeyue.ruoyi.study.module.infra.impl.service.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.common.util.enums.EnumUtils;
import yeyue.ruoyi.study.framework.mybatis.core.query.MyBatisLambdaQueryWrapper;
import yeyue.ruoyi.study.framework.quartz.core.scheduler.SchedulerManager;
import yeyue.ruoyi.study.module.infra.api.domain.job.InfraJobDomain;
import yeyue.ruoyi.study.module.infra.api.service.job.InfraJobService;
import yeyue.ruoyi.study.module.infra.api.service.job.dto.InfraJobCreateReqDTO;
import yeyue.ruoyi.study.module.infra.api.service.job.dto.InfraJobPageReqDTO;
import yeyue.ruoyi.study.module.infra.api.service.job.dto.InfraJobStatusUpdateReqDTO;
import yeyue.ruoyi.study.module.infra.api.service.job.dto.InfraJobUpdateReqDTO;
import yeyue.ruoyi.study.module.infra.impl.entity.job.InfraJobEntity;
import yeyue.ruoyi.study.module.infra.impl.entity.job.convert.InfraJobConvert;
import yeyue.ruoyi.study.module.infra.impl.framework.exception.InfraErrorCode;
import yeyue.ruoyi.study.module.infra.impl.mapper.job.InfraJobMapper;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author yeyue
 * @date 2022-06-02 15:11:27
 */
@Slf4j
@Component
public class InfraJobServiceImpl implements InfraJobService {

    @Resource
    InfraJobMapper mapper;
    @Resource
    SchedulerManager manager;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(InfraJobCreateReqDTO reqDTO) {
        if (mapper.selectByHandlerName(reqDTO.getHandlerName()) != null) {
            throw new ServiceException(InfraErrorCode.JOB_HANDLER_EXISTS);
        }
        InfraJobEntity entity = InfraJobConvert.INSTANCE.toEntity(reqDTO);
        mapper.insert(entity);
        // 添加 Job 到 Quartz 中
        manager.create(entity.getId(), entity.getHandlerName(), entity.getHandlerParam(), entity.getCronExpression(),
                entity.getRetryCount(), entity.getRetryInterval());
        return entity.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(InfraJobUpdateReqDTO reqDTO) {
        InfraJobEntity source = mapper.selectById(reqDTO.getId());
        if (source == null) {
            throw new ServiceException(InfraErrorCode.JOB_NOT_EXISTS);
        }
        if (EnumUtils.notEquals(CommonStatusEnum.ENABLE, CommonStatusEnum::getStatus, source.getStatus())) {
            throw new ServiceException(InfraErrorCode.JOB_UPDATE_ONLY_ENABLE);
        }
        InfraJobEntity entity = InfraJobConvert.INSTANCE.toEntity(reqDTO);
        mapper.updateById(entity);
        manager.update(source.getHandlerName(), entity.getHandlerParam(), entity.getCronExpression(), entity.getRetryCount(), entity.getRetryInterval());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(InfraJobStatusUpdateReqDTO reqDTO) {
        InfraJobEntity source = mapper.selectById(reqDTO.getId());
        if (source == null) {
            throw new ServiceException(InfraErrorCode.JOB_NOT_EXISTS);
        }
        if (Objects.equals(source.getStatus(), reqDTO.getStatus())) {
            return;
        }
        InfraJobEntity entity = InfraJobConvert.INSTANCE.toEntity(reqDTO);
        mapper.updateById(entity);
        if (EnumUtils.equals(CommonStatusEnum.ENABLE, CommonStatusEnum::getStatus, source.getStatus())) {
            manager.resume(source.getHandlerName());
        } else {
            manager.pause(source.getHandlerName());
        }
    }

    @Override
    public void trigger(Long id) {
        InfraJobEntity source = mapper.selectById(id);
        if (source == null) {
            throw new ServiceException(InfraErrorCode.JOB_NOT_EXISTS);
        }
        manager.trigger(source.getId(), source.getHandlerName(), source.getHandlerParam());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        InfraJobEntity source = mapper.selectById(id);
        if (source == null) {
            throw new ServiceException(InfraErrorCode.JOB_NOT_EXISTS);
        }
        mapper.deleteById(id);
        manager.delete(source.getHandlerName());
    }

    @Override
    public InfraJobDomain get(Long id) {
        InfraJobEntity entity = mapper.selectById(id);
        return InfraJobConvert.INSTANCE.toDomain(entity);
    }

    @Override
    public PageResult<InfraJobDomain> list(InfraJobPageReqDTO reqDTO) {
        PageResult<InfraJobEntity> pageResult = mapper.selectPage(reqDTO, new MyBatisLambdaQueryWrapper<InfraJobEntity>()
                .like(InfraJobEntity::getName, reqDTO.getName())
                .like(InfraJobEntity::getHandlerName, reqDTO.getHandlerName())
                .eq(InfraJobEntity::getStatus, reqDTO.getStatus())
        );
        return CollectionUtils.funcPage(pageResult, InfraJobConvert.INSTANCE::toDomain);
    }
}
