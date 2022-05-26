package yeyue.ruoyi.study.module.system.api.service.permission;

import yeyue.ruoyi.study.module.system.api.domain.permission.SystemMenuDomain;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemMenuCreateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemMenuListReqDTO;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemMenuUpdateReqDTO;

import java.util.List;

/**
 * @author yeyue
 * @date 2022-05-23 15:20:16
 */
public interface SystemMenuService {

    /**
     * 创建菜单
     *
     * @param reqDTO 菜单信息
     * @return 菜单id
     */
    Long create(SystemMenuCreateReqDTO reqDTO);

    /**
     * 修改菜单
     *
     * @param reqDTO 菜单信息
     */
    void update(SystemMenuUpdateReqDTO reqDTO);

    /**
     * 获取菜单
     *
     * @param id 菜单id
     * @return 菜单信息
     */
    SystemMenuDomain get(Long id);

    /**
     * 删除菜单
     *
     * @param id 菜单id
     */
    void delete(Long id);

    /**
     * 菜单列表
     *
     * @param reqDTO 查询条件
     * @return 菜单集合
     */
    List<SystemMenuDomain> list(SystemMenuListReqDTO reqDTO);
}
