package yeyue.ruoyi.study.framework.security.core.service;

/**
 * 权限校验接口
 *
 * @author yeyue
 * @date 2022-05-26 09:48:08
 */
public interface SecurityPermissionService {

    /**
     * 判断是否有权限
     *
     * @param permission 权限
     * @return 结果
     */
    boolean hasPermission(String permission);

    /**
     * 判断是否有权限
     *
     * @param permissions 权限
     * @return 结果
     */
    boolean hasAnyPermissions(String... permissions);

    /**
     * 判断是否有权限
     *
     * @param permissions 权限
     * @return 结果
     */
    boolean hasAllPermissions(String... permissions);

    /**
     * 是否有授权
     *
     * @param scope 授权
     * @return 结果
     */
    boolean hasScope(String scope);

    /**
     * 是否有授权
     *
     * @param scopes 授权
     * @return 结果
     */
    boolean hasAnyScopes(String... scopes);

    /**
     * 是否有授权
     *
     * @param scopes 授权
     * @return 结果
     */
    boolean hasAllScopes(String... scopes);
}
