package yeyue.ruoyi.study.module.system.api.service.oauth2;

import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2AccessTokenDomain;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2AccessTokenCreateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2AccessTokenPageReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2AccessTokenRefreshReqDTO;

/**
 * OAuth2.0 Token Service 接口
 * <p>
 * 从功能上，和 Spring Security OAuth 的 DefaultTokenServices + JdbcTokenStore 的功能，提供访问令牌、刷新令牌的操作
 *
 * @author yeyue
 * @date 2022-05-17 12:28:30
 */
public interface SystemOAuth2TokenService {

    /**
     * 生成访问令牌
     * <p>
     * 参考 DefaultTokenServices 的 createAccessToken 方法
     *
     * @param reqDTO 请求参数
     * @return 访问令牌
     */
    SystemOAuth2AccessTokenDomain create(SystemOAuth2AccessTokenCreateReqDTO reqDTO);

    /**
     * 刷新访问令牌
     * <p>
     * 参考 DefaultTokenServices 的 refreshAccessToken 方法
     *
     * @param reqDTO 请求参数
     * @return 访问令牌
     */
    SystemOAuth2AccessTokenDomain refresh(SystemOAuth2AccessTokenRefreshReqDTO reqDTO);

    /**
     * 获取访问令牌
     * <p>
     * 参考 DefaultTokenServices 的 getAccessToken 方法
     *
     * @param accessToken 访问令牌
     * @return 访问令牌
     */
    SystemOAuth2AccessTokenDomain get(String accessToken);

    /**
     * 移除访问令牌
     * <p>
     * 参考 DefaultTokenServices 的 revokeToken 方法
     *
     * @param accessToken 访问令牌
     * @return 访问令牌
     */
    SystemOAuth2AccessTokenDomain remove(String accessToken);

    /**
     * 访问令牌列表
     *
     * @param reqDTO 请求信息
     * @return 结果
     */
    PageResult<SystemOAuth2AccessTokenDomain> list(SystemOAuth2AccessTokenPageReqDTO reqDTO);
}
