package yeyue.ruoyi.study.module.system.impl.service.auth;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2AccessTokenDomain;
import yeyue.ruoyi.study.module.system.api.service.auth.SystemAuthService;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.SystemAuthLoginByUsernameReqDTO;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.SystemAuthLoginRefreshReqDTO;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.SystemAuthLoginRespDTO;
import yeyue.ruoyi.study.module.system.api.service.captcha.SystemCaptchaService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2GrantService;
import yeyue.ruoyi.study.module.system.impl.framework.exception.SystemErrorCode;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author yeyue
 * @date 2022-05-30 13:42:24
 */
@Slf4j
@Component
public class SystemAuthServiceImpl implements SystemAuthService {

    @Resource
    SystemCaptchaService captchaService;
    @Resource
    SystemOAuth2GrantService grantService;

    @Override
    public SystemAuthLoginRespDTO login(SystemAuthLoginByUsernameReqDTO reqDTO) {
        // 1. 验证码校验
        verifyCaptcha(reqDTO.getUuid(), reqDTO.getCode());
        // 2. 登录
        SystemOAuth2AccessTokenDomain result = grantService.password(reqDTO.getClientId(), reqDTO.getUsername(), reqDTO.getPassword(), Collections.emptyList(), null);
        return new SystemAuthLoginRespDTO()
                .setAccessToken(result.getAccessToken())
                .setRefreshToken(result.getRefreshToken())
                .setExpiresTime(result.getExpiresTime());
    }

    @Override
    public void logout(String accessToken) {
        grantService.remove(accessToken);
    }

    @Override
    public SystemAuthLoginRespDTO refresh(SystemAuthLoginRefreshReqDTO reqDTO) {
        SystemOAuth2AccessTokenDomain result = grantService.refresh(reqDTO.getClientId(), reqDTO.getRefreshToken(), null);
        return new SystemAuthLoginRespDTO()
                .setAccessToken(result.getAccessToken())
                .setRefreshToken(result.getRefreshToken())
                .setExpiresTime(result.getExpiresTime());
    }

    private void verifyCaptcha(String uuid, String code) {
        String codeCache = captchaService.getCaptchaCode(uuid);
        if (codeCache == null) {
            throw new ServiceException(SystemErrorCode.AUTH_LOGIN_CAPTCHA_NOT_FOUND);
        }
        // 验证码不正确
        if (!StringUtils.equals(code, codeCache)) {
            throw new ServiceException(SystemErrorCode.AUTH_LOGIN_CAPTCHA_CODE_ERROR);
        }
        // 正确，所以要删除下验证码
        captchaService.deleteCaptchaCode(uuid);
    }
}
