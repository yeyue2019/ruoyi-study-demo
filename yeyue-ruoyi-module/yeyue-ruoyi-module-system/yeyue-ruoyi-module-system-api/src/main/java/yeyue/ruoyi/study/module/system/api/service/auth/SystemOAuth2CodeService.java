package yeyue.ruoyi.study.module.system.api.service.auth;

import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2AccessTokenDomain;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.SystemOAuth2CodeCreateReqDTO;

/**
 * @author yeyue
 * @date 2022-05-17 12:45:39
 */
public interface SystemOAuth2CodeService {

    // TODO: 2022/5/17 每次创建都是新生成一个code

    /**
     * 创建授权码
     *
     * @param reqDTO 请求参数
     * @return 授权码
     */
    String create(SystemOAuth2CodeCreateReqDTO reqDTO);

    // TODO: 2022/5/17 code不允许重复使用

    /**
     * 使用授权码获取信息
     *
     * @param code 授权码
     * @return 授权信息
     */
    SystemOAuth2AccessTokenDomain authorize(String code);
}
