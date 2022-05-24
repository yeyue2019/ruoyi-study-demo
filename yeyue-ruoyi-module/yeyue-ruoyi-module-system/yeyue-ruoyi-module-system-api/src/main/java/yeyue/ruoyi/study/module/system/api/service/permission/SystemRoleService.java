package yeyue.ruoyi.study.module.system.api.service.permission;

import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.module.system.api.domain.permission.SystemRoleDomain;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.*;

import java.util.List;

/**
 * @author yeyue
 * @date 2022-05-24 10:42:53
 */
public interface SystemRoleService {

    /**
     * 创建角色
     *
     * @param reqDTO 角色信息
     * @return 角色Id
     */
    Long create(SystemRoleCreateReqDTO reqDTO);

    /**
     * 修改角色
     *
     * @param reqDTO 角色信息
     */
    void update(SystemRoleUpdateReqDTO reqDTO);

    /**
     * 删除角色
     *
     * @param id 角色Id
     */
    void delete(Long id);

    /**
     * 获取角色信息
     *
     * @param id 角色Id
     * @return 角色信息
     */
    SystemRoleDomain get(Long id);

    /**
     * 角色映射查询
     *
     * @param reqDTO 角色查询条件
     * @return 结果
     */
    List<SystemRoleDomain> list(SystemRoleListReqDTO reqDTO);

    /**
     * 角色列表查询
     *
     * @param reqDTO 角色查询条件
     * @return 结果
     */
    PageResult<SystemRoleDomain> list(SystemRolePageReqDTO reqDTO);
}
