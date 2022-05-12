package yeyue.ruoyi.study.module.system.api.service.dept;

import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.module.system.api.domain.dept.SystemPostDomain;
import yeyue.ruoyi.study.module.system.api.service.dept.dto.*;

/**
 * @author yeyue
 * @date 2022-04-28 16:59:25
 */
public interface SystemPostService {

    /**
     * 创建岗位
     *
     * @param reqDTO 岗位信息
     * @return 岗位编号
     */
    Long create(SystemPostCreateReqDTO reqDTO);

    /**
     * 修改岗位
     *
     * @param reqDTO 岗位信息
     */
    void update(SystemPostUpdateReqDTO reqDTO);

    /**
     * 修改岗位状态
     *
     * @param reqDTO 修改详情
     * @return 修改成功的数量
     */
    int updateStatus(SystemPostStatusUpdateReqDTO reqDTO);

    /**
     * 删除岗位
     *
     * @param id 岗位编号
     */
    void delete(Long id);

    /**
     * 获取岗位
     *
     * @param id 岗位编号
     * @return 岗位信息
     */
    SystemPostDomain get(Long id);

    /**
     * 岗位列表
     *
     * @param reqDTO 查询条件
     * @return 查询结果
     */
    PageResult<SystemPostDomain> list(SystemPostPageReqDTO reqDTO);
}
