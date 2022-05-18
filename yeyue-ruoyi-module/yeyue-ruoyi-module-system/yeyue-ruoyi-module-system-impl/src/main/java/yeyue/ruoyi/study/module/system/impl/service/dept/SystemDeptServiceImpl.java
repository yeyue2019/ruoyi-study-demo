package yeyue.ruoyi.study.module.system.impl.service.dept;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.module.system.api.domain.dept.SystemDeptDomain;
import yeyue.ruoyi.study.module.system.api.enums.DeptIdEnum;
import yeyue.ruoyi.study.module.system.api.service.dept.SystemDeptService;
import yeyue.ruoyi.study.module.system.api.service.dept.dto.*;
import yeyue.ruoyi.study.module.system.impl.mapper.dept.SystemDeptMapper;

import javax.annotation.Resource;

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
        return null;
    }

    @Override
    public void update(SystemDeptUpdateReqDTO reqDTO) {

    }

    @Override
    public SystemDeptDomain get(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public PageResult<SystemDeptPageReqDTO> list(SystemDeptPageReqDTO reqDTO) {
        return null;
    }
}
