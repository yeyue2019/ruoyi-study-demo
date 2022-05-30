package yeyue.ruoyi.study.module.system.api.service.user;

import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.module.system.api.domain.user.SystemUserDomain;
import yeyue.ruoyi.study.module.system.api.service.user.dto.*;

/**
 * 用户管理
 *
 * @author yeyue
 * @date 2022-04-18 16:02:20
 */
public interface SystemUserService {

    /**
     * 创建用户
     *
     * @param create 用户信息
     * @return 用户Id
     */
    Long create(SystemUserCreateReqDTO create);

    /**
     * 修改个人信息
     *
     * @param id     用户Id
     * @param update 用户信息
     */
    void update(Long id, SystemUserProfileUpdateReqDTO update);

    /**
     * 修改部门信息
     *
     * @param update 部门信息
     */
    void update(SystemUserDeptUpdateReqDTO update);

    /**
     * 修改用户密码
     *
     * @param id  用户id
     * @param req 密码信息
     */
    void update(Long id, SystemUserPasswordUpdateReqDTO req);

    /**
     * 重置用户密码
     *
     * @param reqDTO 密码信息
     */
    void replace(SystemUserPasswordReplaceReqDTO reqDTO);

    /**
     * 修改用户状态
     *
     * @param reqDTO 用户信息
     */
    void update(SystemUserStatusUpdateReqDTO reqDTO);

    /**
     * 删除用户
     *
     * @param id 用户Id
     */
    void delete(Long id);

    /**
     * 查询用户
     *
     * @param reqDTO 用户信息
     * @return 结果
     */
    SystemUserDomain get(SystemUserGetReqDTO reqDTO);

    /**
     * 用户列表查询
     *
     * @param req 请求
     * @return 结果
     */
    PageResult<SystemUserDomain> list(SystemUserPageReqDTO req);

    /**
     * 验证账号 + 密码
     *
     * @param username 账号
     * @param password 密码
     * @return 结果
     */
    Long authenticate(String username, String password);
}
