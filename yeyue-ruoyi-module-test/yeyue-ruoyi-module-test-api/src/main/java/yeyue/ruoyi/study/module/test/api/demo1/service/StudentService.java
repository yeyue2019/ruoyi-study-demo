package yeyue.ruoyi.study.module.test.api.demo1.service;

import yeyue.ruoyi.study.framework.common.pojo.PageResult;
import yeyue.ruoyi.study.module.test.api.demo1.domain.StudentDomain;
import yeyue.ruoyi.study.module.test.api.demo1.service.dto.StudentPage;

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
    Long create(StudentDomain create);

    /**
     * 修改学生信息
     *
     * @param update 学生对象
     */
    void update(StudentDomain update);

    /**
     * 获取学生详情
     *
     * @param id 学生Id
     * @return 学生信息
     */
    StudentDomain get(Long id);

    /**
     * 删除学生
     *
     * @param id 学生Id
     * @return 结果
     */
    Integer delete(Long id);

    /**
     * 分页查询学生信息
     *
     * @param page 查询条件
     * @return 结果
     */
    PageResult<StudentDomain> list(StudentPage page);
}
