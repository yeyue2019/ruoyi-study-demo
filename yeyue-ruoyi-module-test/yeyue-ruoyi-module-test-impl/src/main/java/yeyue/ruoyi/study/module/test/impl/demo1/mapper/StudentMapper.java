package yeyue.ruoyi.study.module.test.impl.demo1.mapper;

import org.apache.ibatis.annotations.Mapper;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.framework.mybatis.core.mapper.MyBatisMapper;
import yeyue.ruoyi.study.framework.mybatis.core.query.MyBatisLambdaQueryWrapper;
import yeyue.ruoyi.study.module.test.api.demo1.service.dto.StudentPage;
import yeyue.ruoyi.study.module.test.impl.demo1.entity.StudentEntity;

/**
 * @author yeyue
 * @date 2022-04-10 18:43:02
 */
@Mapper
public interface StudentMapper extends MyBatisMapper<StudentEntity> {

    default PageResult<StudentEntity> selectPage(StudentPage req) {
        return selectPage(req, new MyBatisLambdaQueryWrapper<StudentEntity>()
                .like(StudentEntity::getName, req.getName())
                .eq(StudentEntity::getMobile, req.getMobile())
                .eq(StudentEntity::getGender, req.getGender())
                .between(StudentEntity::getCreateTime, req.getCreateTimeStart(), req.getCreateTimeEnd())
        );
    }
}
