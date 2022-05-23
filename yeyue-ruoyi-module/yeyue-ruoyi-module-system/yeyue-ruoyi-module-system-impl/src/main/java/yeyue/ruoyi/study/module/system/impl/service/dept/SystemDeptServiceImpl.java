package yeyue.ruoyi.study.module.system.impl.service.dept;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.common.util.enums.EnumUtils;
import yeyue.ruoyi.study.framework.mybatis.core.query.MyBatisLambdaQueryWrapper;
import yeyue.ruoyi.study.module.system.api.domain.dept.SystemDeptDomain;
import yeyue.ruoyi.study.module.system.api.enums.dept.DeptIdEnum;
import yeyue.ruoyi.study.module.system.api.service.dept.SystemDeptService;
import yeyue.ruoyi.study.module.system.api.service.dept.dto.*;
import yeyue.ruoyi.study.module.system.impl.entity.dept.SystemDeptEntity;
import yeyue.ruoyi.study.module.system.impl.entity.dept.convert.SystemDeptConvert;
import yeyue.ruoyi.study.module.system.impl.framework.exception.SystemErrorCode;
import yeyue.ruoyi.study.module.system.impl.mapper.dept.SystemDeptMapper;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yeyue
 * @date 2022-05-17 21:43:03
 */
@Slf4j
@Component
public class SystemDeptServiceImpl implements SystemDeptService {

    @Resource
    SystemDeptMapper deptMapper;

    @Override
    public Long create(SystemDeptCreateReqDTO reqDTO) {
        if (reqDTO.getParentId() == null) {
            reqDTO.setParentId(DeptIdEnum.ROOT.getId());
        }
        // 名称校验
        if (deptMapper.selectByParentIdAndName(reqDTO.getParentId(), reqDTO.getName()) != null) {
            throw new ServiceException(SystemErrorCode.DEPT_NAME_DUPLICATE);
        }
        // 上级岗位校验
        if (DeptIdEnum.ROOT.getId().compareTo(reqDTO.getParentId()) != 0) {
            // 父岗位不存在
            SystemDeptEntity dept = deptMapper.selectById(reqDTO.getParentId());
            if (dept == null) {
                throw new ServiceException(SystemErrorCode.DEPT_PARENT_NOT_EXITS);
            }
            // 父部门被禁用
            if (EnumUtils.notEquals(CommonStatusEnum.ENABLE, CommonStatusEnum::getStatus, dept.getStatus())) {
                throw new ServiceException(SystemErrorCode.DEPT_NOT_ENABLE);
            }
        }
        SystemDeptEntity entity = SystemDeptConvert.INSTANCE.toEntity(reqDTO);
        deptMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void update(SystemDeptUpdateReqDTO reqDTO) {
        if (deptMapper.selectById(reqDTO.getId()) == null) {
            throw new ServiceException(SystemErrorCode.DEPT_NOT_FOUND);
        }
        SystemDeptEntity nameCompare = deptMapper.selectByParentIdAndName(reqDTO.getParentId(), reqDTO.getName());
        if (nameCompare != null && !Objects.equals(nameCompare.getId(), reqDTO.getId())) {
            throw new ServiceException(SystemErrorCode.DEPT_NAME_DUPLICATE);
        }
        SystemDeptEntity entity = SystemDeptConvert.INSTANCE.toEntity(reqDTO);
        deptMapper.updateById(entity);
    }

    @Override
    public SystemDeptDomain get(Long id) {
        SystemDeptEntity entity = deptMapper.selectById(id);
        return SystemDeptConvert.INSTANCE.toDomain(entity);
    }

    @Override
    public void delete(Long id) {
        if (deptMapper.selectById(id) == null) {
            throw new ServiceException(SystemErrorCode.DEPT_NOT_FOUND);
        }
        if (deptMapper.selectCount(SystemDeptEntity::getParentId, id) > 0) {
            throw new ServiceException(SystemErrorCode.DEPT_EXITS_CHILDREN);
        }
        deptMapper.deleteById(id);
    }

    @Override
    public List<SystemDeptDomain> list(SystemDeptListReqDTO reqDTO) {
        if (reqDTO.getParentId() == null) {
            reqDTO.setParentId(DeptIdEnum.ROOT.getId());
        }
        List<SystemDeptEntity> entities = deptMapper.selectList(new MyBatisLambdaQueryWrapper<SystemDeptEntity>().eq(SystemDeptEntity::getStatus, reqDTO.getStatus()));
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }
        Map<Long, List<SystemDeptDomain>> tree = entities.stream().map(SystemDeptConvert.INSTANCE::toDomain).collect(Collectors.groupingBy(SystemDeptDomain::getParentId));
        List<SystemDeptDomain> result = new ArrayList<>();
        getDeptByParentId(result, reqDTO.getParentId(), reqDTO.getRecursive() ? Integer.MAX_VALUE : 1, tree);
        return result;
    }

    private void getDeptByParentId(List<SystemDeptDomain> result, Long parentId, int recursiveCount, Map<Long, List<SystemDeptDomain>> tree) {
        // 递归次数为 0，结束！
        if (recursiveCount == 0) {
            return;
        }
        // 获得子部门
        Collection<SystemDeptDomain> depts = tree.get(parentId);
        if (CollectionUtils.isEmpty(depts)) {
            return;
        }
        result.addAll(depts);
        // 继续递归
        depts.forEach(dept -> {
            dept.setChildren(new ArrayList<>());
            getDeptByParentId(dept.getChildren(), dept.getId(), recursiveCount - 1, tree);
        });
    }
}
