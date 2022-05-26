package yeyue.ruoyi.study.module.system.api.service.oauth2;

import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2ClientDomain;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ClientCreateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ClientPageReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ClientUpdateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ClientValidateReqDTO;

/**
 * OAuth2.0 Client Service 接口
 * <p>
 * 从功能上，和 JdbcClientDetailsService 的功能，提供客户端的操作
 *
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

    /**
     * 客户端信息校验
     *
     * @param reqDTO 信息
     * @return 校验结果
     */
    SystemOAuth2ClientDomain validate(SystemOAuth2ClientValidateReqDTO reqDTO);
}
