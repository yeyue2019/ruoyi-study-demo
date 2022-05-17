package yeyue.ruoyi.study.module.system.api.service.auth;

import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.module.system.api.domain.auth.SystemOAuth2ClientDomain;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.*;

/**
 * @author yeyue
 * @date 2022-05-17 09:16:25
 */
public interface SystemOAuth2ClientService {

    /**
     * 创建客户端
     *
     * @param reqDTO 请求对象
     * @return 客户端编号
     */
    Long create(SystemOAuth2ClientCreateReqDTO reqDTO);

    /**
     * 更新客户端
     *
     * @param reqDTO 请求对象
     */
    void update(SystemOAuth2ClientUpdateReqDTO reqDTO);

    /**
     * 更新客户端状态
     *
     * @param reqDTO 请求对象
     * @return 修改数量
     */
    int updateStatus(SystemOAuth2ClientStatusUpdateReqDTO reqDTO);

    /**
     * 删除客户端
     *
     * @param id 客户端id
     */
    void delete(Long id);

    /**
     * 获取客户端
     *
     * @param id 客户端id
     * @return 客户端信息
     */
    SystemOAuth2ClientDomain get(Long id);

    /**
     * 分页获取客户端
     *
     * @param reqDTO 分页条件
     * @return 结果
     */
    PageResult<SystemOAuth2ClientDomain> list(SystemOAuth2ClientPageReqDTO reqDTO);
}
