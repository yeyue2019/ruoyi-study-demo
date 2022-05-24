package yeyue.ruoyi.study.module.system.impl.mapper.user;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.framework.mybatis.core.mapper.MyBatisMapper;
import yeyue.ruoyi.study.framework.mybatis.core.query.MyBatisLambdaQueryWrapper;
import yeyue.ruoyi.study.module.system.api.service.user.dto.SystemUserPageReq;
import yeyue.ruoyi.study.module.system.impl.entity.user.SystemUserEntity;

/**
 * @author yeyue
 * @date 2022-04-18 16:15:20
 */
public interface SystemUserMapper extends MyBatisMapper<SystemUserEntity> {

    default PageResult<SystemUserEntity> selectPage(SystemUserPageReq req) {
        return selectPage(req, new MyBatisLambdaQueryWrapper<>()
        );
    }

    @Update("update system_admin_user set password = #{password},updateTime = now() where deleted = false and id = #{id}")
    void updatePwd(@Param("id") Long id, @Param("password") String passwd);

    @Update("update system_admin_user set status = #{status},updateTime = now() where deleted = false and id = #{id}")
    void updateStatus(@Param("id") Long id, @Param("status") Integer status);
}
