package yeyue.ruoyi.study.module.system.impl.service.dept;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.pojo.pageable.*;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.mybatis.core.query.MyBatisLambdaQueryWrapper;
import yeyue.ruoyi.study.module.system.api.domain.dept.SystemPostDomain;
import yeyue.ruoyi.study.module.system.api.service.dept.SystemPostService;
import yeyue.ruoyi.study.module.system.api.service.dept.dto.*;
import yeyue.ruoyi.study.module.system.impl.entity.dept.SystemPostEntity;
import yeyue.ruoyi.study.module.system.impl.entity.dept.convert.SystemPostConvert;
import yeyue.ruoyi.study.module.system.impl.framework.exception.SystemErrorCode;
import yeyue.ruoyi.study.module.system.impl.mapper.dept.SystemPostMapper;

import javax.annotation.Resource;

/**
 * @author yeyue
 * @date 2022-04-28 17:06:52
 */
@Slf4j
@Component
public class SystemPostServiceImpl implements SystemPostService {

    @Resource
    SystemPostMapper postMapper;

    @Override
    public Long create(SystemPostCreateReqDTO reqDTO) {
        // 岗位名称不能重复
        if (postMapper.selectOne(SystemPostEntity::getName, reqDTO.getName()) != null) {
            throw new ServiceException(SystemErrorCode.POST_NAME_DUPLICATE);
        }
        // 岗位编码不能重复
        if (postMapper.selectOne(SystemPostEntity::getCode, reqDTO.getCode()) != null) {
            throw new ServiceException(SystemErrorCode.POST_CODE_DUPLICATE);
        }
        SystemPostEntity entity = SystemPostConvert.INSTANCE.toEntity(reqDTO);
        postMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void update(SystemPostUpdateReqDTO reqDTO) {
        // 岗位名称是否存在
        if (postMapper.selectById(reqDTO.getId()) == null) {
            throw new ServiceException(SystemErrorCode.POST_NOT_FOUND);
        }
        // 岗位名称不能重复
        SystemPostEntity nameCompare = postMapper.selectOne(SystemPostEntity::getName, reqDTO.getName());
        if (nameCompare != null && nameCompare.getId().compareTo(reqDTO.getId()) != 0) {
            throw new ServiceException(SystemErrorCode.POST_NAME_DUPLICATE);
        }
        // 岗位编码不能重复
        SystemPostEntity codeCompare = postMapper.selectOne(SystemPostEntity::getCode, reqDTO.getCode());
        if (codeCompare != null && codeCompare.getId().compareTo(reqDTO.getId()) != 0) {
            throw new ServiceException(SystemErrorCode.POST_CODE_DUPLICATE);
        }
        SystemPostEntity entity = SystemPostConvert.INSTANCE.toEntity(reqDTO);
        postMapper.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateStatus(SystemPostStatusUpdateReqDTO reqDTO) {
        return postMapper.updateBatchColumnByIds(SystemPostConvert.INSTANCE.toEntity(reqDTO), reqDTO.getIds());
    }

    @Override
    public void delete(Long id) {
        postMapper.deleteById(id);
    }

    @Override
    public SystemPostDomain get(Long id) {
        SystemPostEntity entity = postMapper.selectById(id);
        return SystemPostConvert.INSTANCE.toDomain(entity);
    }

    @Override
    public PageResult<SystemPostDomain> list(SystemPostPageReqDTO reqDTO) {
        reqDTO.addSortParam(SortedParam.asc("sort"));
        PageResult<SystemPostEntity> pageResult = postMapper.selectPage(reqDTO, new MyBatisLambdaQueryWrapper<SystemPostEntity>()
                .like(SystemPostEntity::getCode, reqDTO.getCode())
                .like(SystemPostEntity::getName, reqDTO.getName())
                .eq(SystemPostEntity::getStatus, reqDTO.getStatus()));
        return CollectionUtils.convertPage(pageResult, SystemPostConvert.INSTANCE::toDomain);
    }
}
