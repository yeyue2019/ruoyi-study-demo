package yeyue.ruoyi.study.module.system.api.service.oauth2;

import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ApproveCheckReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ApproveGetReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ApproveUpdateReqDTO;

import java.util.Set;

/**
 * @author yeyue
 * @date 2022-05-26 16:26:07
 */
public interface SystemOAuth2ApproveService {

    /**
     * 获取用户的批准列表
     *
     * @param reqDTO 用户信息
     * @return 批准列表
     */
    Set<String> get(SystemOAuth2ApproveGetReqDTO reqDTO);

    /**
     * 校验用户申请的权限列表
     *
     * @param reqDTO 校验信息
     * @return 结果
     */
    boolean check(SystemOAuth2ApproveCheckReqDTO reqDTO);

    /**
     * 用户申请授权
     *
     * @param reqDTO 权限信息
     */
    void update(SystemOAuth2ApproveUpdateReqDTO reqDTO);
}
