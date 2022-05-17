package yeyue.ruoyi.study.module.system.api.service.auth;

import yeyue.ruoyi.study.module.system.api.domain.auth.SystemOAuth2AccessTokenDomain;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.SystemOAuth2AccessTokenCreateReqDTO;

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
    SystemOAuth2AccessTokenDomain createAccessToken(SystemOAuth2AccessTokenCreateReqDTO reqDTO);
}
