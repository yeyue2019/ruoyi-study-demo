package yeyue.ruoyi.study.module.test.impl.service.demo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import yeyue.ruoyi.study.framework.common.pojo.PageResult;
import yeyue.ruoyi.study.framework.redis.core.RedisRepository;
import yeyue.ruoyi.study.module.test.api.domain.demo1.Student;
import yeyue.ruoyi.study.module.test.api.enums.GenderEnum;
import yeyue.ruoyi.study.module.test.api.service.demo1.StudentService;
import yeyue.ruoyi.study.module.test.api.service.demo1.dto.StudentPage;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.*;

/**
 * @author yeyue
 * @date 2022-04-10 14:27:16
 */
@Slf4j
@Validated
@Component
public class StudentServiceImpl implements StudentService {

    @Resource
    RedisRepository redisRepository;

    @Override
    public Long create(Student create) {
        log.info("接口调用成功：create");
        return 1L;
    }

    @Override
    public void update(Student update) {
        log.warn("接口调用成功：update");
    }

    @Override
    public Student get(Long id) {
        log.warn("接口调用成功：get");
        Student student = new Student();
        student.setId(1L)
                .setName("夜月")
                .setIdCard("37028419281201")
                .setMobile("14234320019")
                .setBirthday(LocalDate.of(2000, 10, 1))
                .setGender(GenderEnum.男.name())
                .setGraduate(true)
                .setGrade(BigDecimal.valueOf(89.93))
                .setAwardNumber(12)
                .setDescription("优秀学生")
                .setCreateTime(LocalDateTime.now().plusYears(-12))
                .setUpdateTime(LocalDateTime.now().plusYears(-6));
        return student;
    }

    @Override
    public Integer delete(Long id) {
        log.error("接口调用成功：delete");
        return 0;
    }

    @Override
    public PageResult<Student> list(StudentPage page) {
        log.info("接口调用成功：list");
        return PageResult.empty();
    }
}
