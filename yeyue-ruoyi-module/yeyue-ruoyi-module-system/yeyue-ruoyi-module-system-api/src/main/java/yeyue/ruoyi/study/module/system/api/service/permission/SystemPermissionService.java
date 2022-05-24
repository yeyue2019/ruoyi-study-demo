package yeyue.ruoyi.study.module.system.api.service.permission;

import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemPermissionAssignMenuReqDTO;

/**
 * @author yeyue
 * @date 2022-05-24 16:42:47
 */
public interface SystemPermissionService {

    /**
     * 赋予角色权限
     *
     * @param reqDTO 信息
     */
    void assignRoleMenu(SystemPermissionAssignMenuReqDTO reqDTO);
}
