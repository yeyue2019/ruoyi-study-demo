package yeyue.ruoyi.study.module.system.api.service.oauth2;

import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2CodeDomain;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2CodeCreateReqDTO;

/**
 * OAuth2.0 授权码 Service 接口
 * <p>
 * 从功能上，和 Spring Security OAuth 的 JdbcAuthorizationCodeServices 的功能，提供授权码的操作
 *
 * @author yeyue
 * @date 2022-05-17 12:45:39
 */
public interface SystemOAuth2CodeService {

    /**
     * 创建授权码
     * <p>
     * 参考 JdbcAuthorizationCodeServices 的 createAuthorizationCode 方法
     *
     * @param reqDTO 请求参数
     * @return 授权码
     */
    String create(SystemOAuth2CodeCreateReqDTO reqDTO);

    /**
     * 使用授权码
     *
     * @param code 授权码
     * @return 授权信息
     */
    SystemOAuth2CodeDomain consume(String code);
}
