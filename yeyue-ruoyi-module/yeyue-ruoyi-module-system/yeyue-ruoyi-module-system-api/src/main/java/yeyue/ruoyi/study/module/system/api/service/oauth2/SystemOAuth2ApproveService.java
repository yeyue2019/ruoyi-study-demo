package yeyue.ruoyi.study.module.system.api.service.oauth2;

import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2ClientDomain;

import java.util.List;
import java.util.Set;

/**
 * @author yeyue
 * @date 2022-05-26 16:26:07
 */
public interface SystemOAuth2ApproveService {

    /**
     * 获取用户的批准列表
     *
     * @param userId   用户id
     * @param userType 用户类型
     * @param clientId 客户端Id
     * @param client   客户端
     * @return 批准列表
     */
    Set<String> get(String userId, Integer userType, String clientId, SystemOAuth2ClientDomain client);

    /**
     * 校验用户申请的权限列表
     *
     * @param userId   用户id
     * @param userType 用户类型
     * @param clientId 客户端Id
     * @param scopes   申请的权限列表
     * @param client   客户端
     * @return 结果
     */
    boolean check(String userId, Integer userType, String clientId, List<String> scopes, SystemOAuth2ClientDomain client);

    /**
     * 用户申请授权
     *
     * @param userId   用户id
     * @param userType 用户类型
     * @param clientId 客户端Id
     * @param scopes   申请的权限列表
     * @param client   客户端
     */
    void update(String userId, Integer userType, String clientId, List<String> scopes, SystemOAuth2ClientDomain client);
}
