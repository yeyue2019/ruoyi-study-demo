package yeyue.ruoyi.study.module.test.api.service.demo1;

import yeyue.ruoyi.study.framework.common.pojo.PageResult;
import yeyue.ruoyi.study.module.test.api.domain.demo1.Student;
import yeyue.ruoyi.study.module.test.api.service.demo1.dto.StudentPage;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author yeyue
 * @date 2022-04-10 11:40:00
 */
public interface StudentService {

    /**
     * 添加学生
     *
     * @param create 学生对象
     * @return 学生Id
     */
    Long create(@Valid Student create);

    /**
     * 修改学生信息
     *
     * @param update 学生对象
     */
    void update(@Valid Student update);

    /**
     * 获取学生详情
     *
     * @param id 学生Id
     * @return 学生信息
     */
    Student get(@NotNull(message = "学生Id不可为空") Long id);

    /**
     * 删除学生
     *
     * @param id 学生Id
     * @return 结果
     */
    Integer delete(@NotNull(message = "学生Id不可为空") Long id);

    /**
     * 分页查询学生信息
     *
     * @param page 查询条件
     * @return 结果
     */
    PageResult<Student> list(@Valid StudentPage page);
}
