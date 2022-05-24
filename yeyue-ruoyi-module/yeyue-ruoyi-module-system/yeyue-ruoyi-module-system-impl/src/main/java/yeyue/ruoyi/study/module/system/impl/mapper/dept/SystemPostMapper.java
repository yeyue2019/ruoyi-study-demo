package yeyue.ruoyi.study.module.system.impl.mapper.dept;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import yeyue.ruoyi.study.framework.mybatis.core.mapper.MyBatisMapper;
import yeyue.ruoyi.study.module.system.impl.entity.dept.SystemPostEntity;

import java.util.Collection;

/**
 * @author yeyue
 * @date 2022-04-28 17:04:51
 */
public interface SystemPostMapper extends MyBatisMapper<SystemPostEntity> {

    @Update("<script>" + "update ruoyi_system_post set status = #{status} where deleted = false and id in " + "<foreach item='id' index='index' collection='ids' open='(' separator=',' close=')'>" + "#{id}" + "</foreach>" + "</script>")
    @Deprecated
    int updateStatus(@Param("ids") Collection<Long> ids, @Param("status") Integer status);

    default SystemPostEntity selectByName(String name) {
        return selectOne(SystemPostEntity::getName, name);
    }

    default SystemPostEntity selectByCode(String code) {
        return selectOne(SystemPostEntity::getCode, code);
    }
}
