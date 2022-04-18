package yeyue.ruoyi.study.module.system.api.service.user;

import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.module.system.api.domain.user.AdminUserDomain;
import yeyue.ruoyi.study.module.system.api.service.user.dto.*;

/**
 * 用户管理
 *
 * @author yeyue
 * @date 2022-04-18 16:02:20
 */
public interface AdminUserService {

    /**
     * 创建用户
     *
     * @param create 用户信息
     * @return 用户Id
     */
    Long create(AdminUserDomain create);

    /**
     * 修改信息
     *
     * @param update 用户信息
     */
    void update(AdminUserDomain update);

    /**
     * 修改用户密码
     *
     * @param id  用户id
     * @param req 密码信息
     */
    void updatePasswd(Long id, AdminUserUpdatePasswdReqDTO req);

    /**
     * 修改用户状态
     *
     * @param id     用户Id
     * @param status 状态
     */
    void updateStatus(Long id, Integer status);

    /**
     * 删除用户
     *
     * @param id 用户Id
     */
    Integer delete(Long id);

    /**
     * 根据用户Id查询用户
     *
     * @param id 用户Id
     * @return 结果
     */
    AdminUserDomain getById(Long id);

    /**
     * 根据用户账号查询用户
     *
     * @param username 用户账号
     * @return 结果
     */
    AdminUserDomain getByUsername(String username);

    /**
     * 用户列表查询
     *
     * @param req 请求
     * @return 结果
     */
    PageResult<AdminUserDomain> list(AdminUserPageReqDTO req);
}
