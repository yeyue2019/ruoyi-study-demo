package yeyue.ruoyi.study.module.test.impl.demo1.mapper;

import org.apache.ibatis.annotations.Mapper;
import yeyue.ruoyi.study.framework.mybatis.core.mapper.MyBatisMapper;
import yeyue.ruoyi.study.module.test.impl.demo1.entity.StudentEntity;

/**
 * @author yeyue
 * @date 2022-04-10 18:43:02
 */
@Mapper
public interface StudentMapper extends MyBatisMapper<StudentEntity> {
}
