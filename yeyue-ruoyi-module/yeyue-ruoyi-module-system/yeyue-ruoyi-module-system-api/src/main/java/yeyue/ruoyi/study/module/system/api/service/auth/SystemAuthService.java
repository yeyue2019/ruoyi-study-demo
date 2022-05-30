package yeyue.ruoyi.study.module.system.api.service.auth;

import yeyue.ruoyi.study.module.system.api.service.auth.dto.SystemAuthLoginByUsernameReqDTO;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.SystemAuthLoginRefreshReqDTO;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.SystemAuthLoginRespDTO;

/**
 * @author yeyue
 * @date 2022-05-29 22:56:50
 */
public interface SystemAuthService {

    /**
     * 登录
     *
     * @param reqDTO 请求
     * @return 结果
     */
    SystemAuthLoginRespDTO login(SystemAuthLoginByUsernameReqDTO reqDTO);

    /**
     * 登出
     *
     * @param accessToken 访问令牌
     */
    void logout(String accessToken);

    /**
     * 刷新访问令牌
     *
     * @param reqDTO 刷新令牌
     * @return 结果
     */
    SystemAuthLoginRespDTO refresh(SystemAuthLoginRefreshReqDTO reqDTO);
}
