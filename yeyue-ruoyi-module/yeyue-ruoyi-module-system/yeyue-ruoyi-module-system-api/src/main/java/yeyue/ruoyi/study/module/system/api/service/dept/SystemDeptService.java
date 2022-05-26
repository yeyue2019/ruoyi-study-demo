package yeyue.ruoyi.study.module.system.api.service.dept;

import java.util.List;

import yeyue.ruoyi.study.module.system.api.domain.dept.SystemDeptDomain;
import yeyue.ruoyi.study.module.system.api.service.dept.dto.*;

/**
 * @author yeyue
 * @date 2022-05-17 21:26:17
 */
public interface SystemDeptService {

    /**
     * 新增部门
     *
     * @param reqDTO 部门信息
     * @return 部门编号
     */
    Long create(SystemDeptCreateReqDTO reqDTO);

    /**
     * 更新部门
     *
     * @param reqDTO 部门信息
     */
    void update(SystemDeptUpdateReqDTO reqDTO);

    /**
     * 获取部门
     *
     * @param id 部门编号
     * @return 部门信息
     */
    SystemDeptDomain get(Long id);

    /**
     * 删除部门
     *
     * @param id 部门编号
     */
    void delete(Long id);

    /**
     * 部门集合
     *
     * @param reqDTO 查询条件
     * @return 结果
     */
    List<SystemDeptDomain> list(SystemDeptListReqDTO reqDTO);
}
