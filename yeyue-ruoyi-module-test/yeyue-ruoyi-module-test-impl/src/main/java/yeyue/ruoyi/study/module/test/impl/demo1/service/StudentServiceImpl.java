package yeyue.ruoyi.study.module.test.impl.demo1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.framework.common.pojo.PageResult;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.redis.core.RedisRepository;
import yeyue.ruoyi.study.module.test.api.demo1.domain.StudentDomain;
import yeyue.ruoyi.study.module.test.api.demo1.service.StudentService;
import yeyue.ruoyi.study.module.test.api.demo1.service.dto.StudentPage;
import yeyue.ruoyi.study.module.test.impl.demo1.entity.StudentEntity;
import yeyue.ruoyi.study.module.test.impl.demo1.entity.convert.StudentConvert;
import yeyue.ruoyi.study.module.test.impl.demo1.mapper.StudentMapper;

import javax.annotation.Resource;

/**
 * @author yeyue
 * @date 2022-04-10 14:27:16
 */
@Slf4j
@Component
public class StudentServiceImpl implements StudentService {

    @Resource
    RedisRepository redisRepository;
    @Resource
    StudentMapper mapper;

    @Override
    public Long create(StudentDomain create) {
        StudentEntity entity = StudentConvert.toStudentEntity(create);
        mapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void update(StudentDomain update) {
        StudentEntity entity = StudentConvert.toStudentEntity(update);
        mapper.updateById(entity);
    }

    @Override
    public StudentDomain get(Long id) {
        StudentEntity entity = mapper.selectById(id);
        return StudentConvert.toStudentDomain(entity);
    }

    @Override
    public Integer delete(Long id) {
        return mapper.deleteById(id);
    }

    @Override
    public PageResult<StudentDomain> list(StudentPage page) {
        return CollectionUtils.convertPage(mapper.selectPage(page), StudentConvert::toStudentDomain);
    }
}
