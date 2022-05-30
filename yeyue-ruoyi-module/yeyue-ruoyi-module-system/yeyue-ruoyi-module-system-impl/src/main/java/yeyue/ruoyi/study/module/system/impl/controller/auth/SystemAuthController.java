package yeyue.ruoyi.study.module.system.impl.controller.auth;

import com.github.xiaoymin.knife4j.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.security.core.util.SecurityUtils;
import yeyue.ruoyi.study.module.system.api.domain.permission.SystemMenuDomain;
import yeyue.ruoyi.study.module.system.api.domain.permission.SystemRoleDomain;
import yeyue.ruoyi.study.module.system.api.domain.user.SystemUserDomain;
import yeyue.ruoyi.study.module.system.api.service.auth.SystemAuthService;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.SystemAuthLoginByUsernameReqDTO;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.SystemAuthLoginRefreshReqDTO;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.SystemAuthLoginRespDTO;
import yeyue.ruoyi.study.module.system.api.service.permission.SystemPermissionService;
import yeyue.ruoyi.study.module.system.api.service.user.SystemUserService;
import yeyue.ruoyi.study.module.system.api.service.user.dto.SystemUserGetReqDTO;
import yeyue.ruoyi.study.module.system.api.service.user.dto.SystemUserPasswordUpdateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.user.dto.SystemUserProfileUpdateReqDTO;
import yeyue.ruoyi.study.module.system.impl.controller.auth.vo.SystemAuthMenuRespVO;
import yeyue.ruoyi.study.module.system.impl.controller.auth.vo.SystemAuthPermissionRespVO;
import yeyue.ruoyi.study.module.system.impl.controller.user.vo.SystemUserRespVO;
import yeyue.ruoyi.study.module.system.impl.entity.permission.convert.SystemMenuConvert;
import yeyue.ruoyi.study.module.system.impl.entity.user.convert.SystemUserConvert;
import yeyue.ruoyi.study.module.system.impl.framework.security.util.SystemSecurityUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * @author yeyue
 * @date 2022-05-30 16:15:25
 */
@Api(tags = "用户认证")
@RestController
@RequestMapping("/web/sys/auth")
public class SystemAuthController {

    @Resource
    SystemAuthService authService;
    @Resource
    SystemUserService userService;
    @Resource
    SystemPermissionService permissionService;


    @ApiOperation(value = "账号密码登录")
    @PostMapping("/login-password")
    public CommonResult<SystemAuthLoginRespDTO> login(@RequestBody @Valid SystemAuthLoginByUsernameReqDTO reqDTO) {
        return CommonResult.success(authService.login(reqDTO));
    }

    @ApiOperation(value = "登出系统")
    @PostMapping("/logout")
    public CommonResult<Void> logout(HttpServletRequest request) {
        String token = SecurityUtils.obtainAuthorization(request);
        if (StrUtil.isNotBlank(token)) {
            authService.logout(token);
        }
        return CommonResult.success();
    }

    @ApiOperation(value = "刷新访问令牌")
    @PostMapping("/refresh-token")
    @ApiImplicitParam(name = "refreshToken", value = "刷新令牌", required = true, dataTypeClass = String.class)
    public CommonResult<SystemAuthLoginRespDTO> refreshToken(@RequestBody @Valid SystemAuthLoginRefreshReqDTO reqDTO) {
        return CommonResult.success(authService.refresh(reqDTO));
    }

    @ApiOperation(value = "个人信息")
    @GetMapping("/get-profile")
    public CommonResult<SystemUserRespVO> getProfile() {
        SystemUserDomain domain = userService.get(new SystemUserGetReqDTO().setId(SystemSecurityUtils.getUserId()));
        return CommonResult.success(SystemUserConvert.INSTANCE.toVo(domain));
    }

    @ApiOperation(value = "用户权限")
    @GetMapping("/get-permission")
    public CommonResult<SystemAuthPermissionRespVO> getPermission() {
        Long userId = SystemSecurityUtils.getUserId();
        SystemAuthPermissionRespVO vo = new SystemAuthPermissionRespVO();
        Set<SystemRoleDomain> roles = permissionService.getUserRoleIds(userId, CommonStatusEnum.ENABLE.getStatus());
        vo.setRoles(CollectionUtils.funcSet(roles, SystemRoleDomain::getCode));
        Set<SystemMenuDomain> menus = permissionService.getRoleMenuIds(CollectionUtils.funcSet(roles, SystemRoleDomain::getId), CommonStatusEnum.ENABLE.getStatus());
        vo.setPermissions(CollectionUtils.funcSet(menus, SystemMenuDomain::getPermission));
        return CommonResult.success(vo);
    }

    @ApiOperation(value = "用户菜单")
    @GetMapping("/get-menu")
    public CommonResult<List<SystemAuthMenuRespVO>> getMenu() {
        Long userId = SystemSecurityUtils.getUserId();
        Set<SystemMenuDomain> menus = permissionService.getRoleMenuIds(CollectionUtils.funcSet(permissionService.getUserRoleIds(userId, CommonStatusEnum.ENABLE.getStatus()), SystemRoleDomain::getId), CommonStatusEnum.ENABLE.getStatus());
        return CommonResult.success(SystemMenuConvert.INSTANCE.buildMenuTree(menus));
    }

    @ApiOperation(value = "更改资料")
    @PostMapping("/update-profile")
    public CommonResult<Void> update(@Valid @RequestBody SystemUserProfileUpdateReqDTO req) {
        userService.update(SystemSecurityUtils.getUserId(), req);
        return CommonResult.success();
    }

    @ApiOperation(value = "更改密码")
    @PostMapping("/update-password")
    public CommonResult<Void> update(@Valid @RequestBody SystemUserPasswordUpdateReqDTO req) {
        userService.update(SystemSecurityUtils.getUserId(), req);
        return CommonResult.success();
    }
}
