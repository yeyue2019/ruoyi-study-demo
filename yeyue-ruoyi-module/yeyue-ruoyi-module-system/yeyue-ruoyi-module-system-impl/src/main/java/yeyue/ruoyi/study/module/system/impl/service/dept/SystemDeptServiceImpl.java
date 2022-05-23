package yeyue.ruoyi.study.module.system.impl.service.dept;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.common.util.enums.EnumUtils;
import yeyue.ruoyi.study.framework.redis.core.RedisRepository;
import yeyue.ruoyi.study.framework.redis.domain.RedisDomainDefine;
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
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author yeyue
 * @date 2022-05-17 21:43:03
 */
@Slf4j
@Component
public class SystemDeptServiceImpl implements SystemDeptService {
    public static final String REDIS_SYSTEM_DEPT_TREE_KEY = "system:dept:tree";
    public static final TypeReference<Map<Long, List<SystemDeptDomain>>> REDIS_SYSTEM_DEPT_TREE_TYPE = new TypeReference<Map<Long, List<SystemDeptDomain>>>() {
    };

    @Resource
    SystemDeptMapper deptMapper;
    @Resource
    RedisRepository redisRepository;

    @Override
    public Long create(SystemDeptCreateReqDTO reqDTO) {
        if (reqDTO.getParentId() == null) {
            reqDTO.setParentId(DeptIdEnum.ROOT.getId());
        }
        if (deptMapper.selectByParentIdAndName(reqDTO.getParentId(), reqDTO.getName()) != null) {
            throw new ServiceException(SystemErrorCode.DEPT_NAME_DUPLICATE);
        }
        checkParentDeptEnable(null, reqDTO.getParentId());
        SystemDeptEntity entity = SystemDeptConvert.INSTANCE.toEntity(reqDTO);
        deptMapper.insert(entity);
        clearCache();
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
        checkParentDeptEnable(reqDTO.getId(), reqDTO.getParentId());
        SystemDeptEntity entity = SystemDeptConvert.INSTANCE.toEntity(reqDTO);
        deptMapper.updateById(entity);
        clearCache();
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
        clearCache();
    }

    @Override
    public List<SystemDeptDomain> list(Long parentId, Boolean recursive) {
        if (parentId == null) {
            return Collections.emptyList();
        }
        Map<Long, List<SystemDeptDomain>> tree = deptTree();
        List<SystemDeptDomain> result = new ArrayList<>();
        getDeptByParentId(result, parentId, recursive ? Integer.MAX_VALUE : 1, tree);
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
            getDeptByParentId(dept.getChildren(), dept.getId(),
                    recursiveCount - 1, tree);
        });
    }


    private void checkParentDeptEnable(Long id, Long parentId) {
        // 不能设置自己为父部门
        if (Objects.equals(id, parentId)) {
            throw new ServiceException(SystemErrorCode.D_PARENT_ERROR);
        }
        if (DeptIdEnum.ROOT.getId().compareTo(parentId) != 0) {
            // 父岗位不存在
            SystemDeptEntity dept = deptMapper.selectById(parentId);
            if (dept == null) {
                throw new ServiceException(SystemErrorCode.DEPT_PARENT_NOT_EXITS);
            }
            // 父部门被禁用
            if (EnumUtils.notEquals(CommonStatusEnum.ENABLE, CommonStatusEnum::getStatus, dept.getStatus())) {
                throw new ServiceException(SystemErrorCode.DEPT_NOT_ENABLE);
            }
        }
        // 父部门不能是原来的子部门
        if (id != null) {
            Map<Long, List<SystemDeptDomain>> tree = deptTree();
            if (checkIdIfChild(parentId, id, tree)) {
                throw new ServiceException(SystemErrorCode.DEPT_PARENT_IS_CHILD);
            }
        }
    }


    /**
     * 校验部门是否是下级
     *
     * @param id      当前id
     * @param compare 要比较的id
     * @param tree    获取的继承树
     * @return 结果
     */
    private boolean checkIdIfChild(Long id, Long compare, Map<Long, List<SystemDeptDomain>> tree) {
        return tree.getOrDefault(id, Collections.emptyList()).stream().anyMatch(r -> Objects.equals(r.getId(), compare));
    }


    /**
     * 获取部门继承树
     *
     * @return 部门关系树
     */
    private Map<Long, List<SystemDeptDomain>> deptTree() {
        Map<Long, List<SystemDeptDomain>> tree = redisRepository.get(REDIS_SYSTEM_DEPT_TREE_KEY, DeptIdEnum.ROOT.getName(), REDIS_SYSTEM_DEPT_TREE_TYPE);
        if (tree != null) {
            return tree;
        }
        List<SystemDeptEntity> entities = deptMapper.selectList();
        tree = Collections.emptyMap();
        if (CollectionUtils.isNotEmpty(entities)) {
            tree = entities
                    .stream().map(SystemDeptConvert.INSTANCE::toDomain)
                    .collect(Collectors.groupingBy(SystemDeptDomain::getParentId));
        }
        redisRepository.save(REDIS_SYSTEM_DEPT_TREE_KEY, new RedisDomainDefine<>(DeptIdEnum.ROOT.getName(), tree, 30, TimeUnit.DAYS));
        return tree;
    }

    private void clearCache() {
        redisRepository.delete(REDIS_SYSTEM_DEPT_TREE_KEY, DeptIdEnum.ROOT.getName());
    }
}
