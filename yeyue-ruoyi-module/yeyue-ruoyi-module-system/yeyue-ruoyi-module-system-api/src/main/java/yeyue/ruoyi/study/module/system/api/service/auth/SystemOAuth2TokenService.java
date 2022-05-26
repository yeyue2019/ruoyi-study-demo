package yeyue.ruoyi.study.module.system.api.service.auth;

import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2AccessTokenDomain;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.SystemOAuth2AccessTokenCreateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.SystemOAuth2AccessTokenRefreshReqDTO;

/**
 * @author yeyue
 * @date 2022-05-17 12:28:30
 */
public interface SystemOAuth2TokenService {

    /**
     * 生成访问令牌
     *
     * @param reqDTO 请求参数
     * @return 访问令牌
     */
    SystemOAuth2AccessTokenDomain create(SystemOAuth2AccessTokenCreateReqDTO reqDTO);

    /**
     * 刷新访问令牌
     *
     * @param reqDTO 请求参数
     * @return 访问令牌
     */
    SystemOAuth2AccessTokenDomain refresh(SystemOAuth2AccessTokenRefreshReqDTO reqDTO);

    /**
     * 获取访问令牌
     *
     * @param accessToken 访问令牌
     * @return 访问令牌
     */
    SystemOAuth2AccessTokenDomain get(String accessToken);

    /**
     * 移除访问令牌
     *
     * @param accessToken 访问令牌
     * @return 访问令牌
     */
    SystemOAuth2AccessTokenDomain remove(String accessToken);
}
