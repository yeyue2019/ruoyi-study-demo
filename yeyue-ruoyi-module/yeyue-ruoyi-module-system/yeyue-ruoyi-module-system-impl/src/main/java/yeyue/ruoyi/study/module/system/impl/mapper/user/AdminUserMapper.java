package yeyue.ruoyi.study.module.system.impl.mapper.user;

import org.apache.ibatis.annotations.*;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.framework.mybatis.core.mapper.MyBatisMapper;
import yeyue.ruoyi.study.framework.mybatis.core.query.MyBatisLambdaQueryWrapper;
import yeyue.ruoyi.study.module.system.api.service.user.dto.AdminUserPageReqDTO;
import yeyue.ruoyi.study.module.system.impl.entity.user.AdminUserEntity;

/**
 * @author yeyue
 * @date 2022-04-18 16:15:20
 */
public interface AdminUserMapper extends MyBatisMapper<AdminUserEntity> {

    default PageResult<AdminUserEntity> selectPage(AdminUserPageReqDTO req) {
        return selectPage(req, new MyBatisLambdaQueryWrapper<AdminUserEntity>()
        );
    }

    @Update("update system_admin_user set password = #{password},updateTime = now() where deleted = false and id = #{id}")
    int updatePasswd(@Param("id") Long id, @Param("password") String passwd);

    @Update("update system_admin_user set status = #{status},updateTime = now() where deleted = false and id = #{id}")
    void updateStatus(@Param("id") Long id, @Param("status") Integer status);
}
