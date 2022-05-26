package yeyue.ruoyi.study.module.system.api.service.oauth2;

import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2AccessTokenDomain;

import java.util.List;

/**
 * OAuth2 授予 Service 接口
 * <p>
 * 从功能上，和 Spring Security OAuth 的 TokenGranter 的功能，提供访问令牌、刷新令牌的操作
 * <p>
 * 将自身的 AdminUser 用户，授权给第三方应用，采用 OAuth2.0 的协议。
 * <p>
 * 问题：为什么自身也作为一个第三方应用，也走这套流程呢？
 * 回复：当然可以这么做，采用 Implicit 模式。考虑到大多数开发者使用不到这个特性，OAuth2.0 毕竟有一定学习成本，所以暂时没有采取这种方式。
 *
 * @author yeyue
 * @date 2022-05-26 14:41:13
 */
public interface SystemOAuth2GrantService {

    /**
     * 简化模式
     * <p>
     * 对应 Spring Security OAuth2 的 ImplicitTokenGranter 功能
     *
     * @param userId                      用户Id
     * @param userType                    用户类型
     * @param clientId                    客户端Id
     * @param scopes                      授权范围
     * @param accessTokenValiditySeconds  访问令牌有效期
     * @param refreshTokenValiditySeconds 刷新令牌有效期
     * @return 访问令牌
     */
    SystemOAuth2AccessTokenDomain implicit(String userId, Integer userType, String clientId, List<String> scopes, Integer accessTokenValiditySeconds, Integer refreshTokenValiditySeconds);

    /**
     * 授权码模式，第一阶段，获得 code 授权码
     * <p>
     * 对应 Spring Security OAuth2 的 AuthorizationEndpoint 的 generateCode 方法
     *
     * @param userId              用户编号
     * @param userType            用户类型
     * @param clientId            客户端编号
     * @param scopes              授权范围
     * @param redirectUri         重定向 URI
     * @param state               状态
     * @param codeValiditySeconds 授权码有效期
     * @return 授权码
     */
    String authorizationCode(String userId, Integer userType, String clientId, List<String> scopes, String redirectUri, String state, Integer codeValiditySeconds);

    /**
     * 授权码模式，第二阶段，获得 accessToken 访问令牌
     * <p>
     * 对应 Spring Security OAuth2 的 AuthorizationCodeTokenGranter 功能
     *
     * @param clientId                    客户端编号
     * @param code                        授权码
     * @param accessTokenValiditySeconds  访问令牌有效期
     * @param refreshTokenValiditySeconds 刷新令牌有效期
     * @return 访问令牌
     */
    SystemOAuth2AccessTokenDomain authorizationCode(String code, String clientId, Integer accessTokenValiditySeconds, Integer refreshTokenValiditySeconds);

    /**
     * 密码模式
     * <p>
     * 对应 Spring Security OAuth2 的 ResourceOwnerPasswordTokenGranter 功能
     *
     * @param userId                      用户Id
     * @param userType                    用户类型
     * @param clientId                    客户端编号
     * @param scopes                      授权范围
     * @param accessTokenValiditySeconds  访问令牌有效期
     * @param refreshTokenValiditySeconds 刷新令牌有效期
     * @return 访问令牌
     */
    SystemOAuth2AccessTokenDomain password(String userId, Integer userType, String clientId, List<String> scopes, Integer accessTokenValiditySeconds, Integer refreshTokenValiditySeconds);

    /**
     * 刷新模式
     * <p>
     * 对应 Spring Security OAuth2 的 ResourceOwnerPasswordTokenGranter 功能
     *
     * @param refreshToken               刷新令牌
     * @param clientId                   客户端编号
     * @param accessTokenValiditySeconds 访问令牌有效期
     * @return 访问令牌
     */
    SystemOAuth2AccessTokenDomain refresh(String refreshToken, String clientId, Integer accessTokenValiditySeconds);

    /**
     * 客户端模式
     * <p>
     * 对应 Spring Security OAuth2 的 ClientCredentialsTokenGranter 功能
     *
     * @param userId                      用户Id
     * @param userType                    用户类型
     * @param clientId                    客户端编号
     * @param scopes                      授权范围
     * @param accessTokenValiditySeconds  访问令牌有效期
     * @param refreshTokenValiditySeconds 刷新令牌有效期
     * @return 访问令牌
     */
    SystemOAuth2AccessTokenDomain clientCredentials(String userId, Integer userType, String clientId, List<String> scopes, Integer accessTokenValiditySeconds, Integer refreshTokenValiditySeconds);
}
