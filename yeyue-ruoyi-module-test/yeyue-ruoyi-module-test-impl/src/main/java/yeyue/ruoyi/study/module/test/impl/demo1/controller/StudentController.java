package yeyue.ruoyi.study.module.test.impl.demo1.controller;

import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.module.test.api.demo1.domain.StudentDomain;
import yeyue.ruoyi.study.module.test.api.demo1.service.StudentService;
import yeyue.ruoyi.study.module.test.api.demo1.service.dto.StudentPage;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

/**
 * @author yeyue
 * @date 2022-04-10 14:39:51
 */
@Api(tags = "学生管理")
@Validated
@RestController
@RequestMapping("/test/demo1/student")
public class StudentController {

    @Resource
    StudentService service;

    @ApiOperation(value = "增加学生")
    @PutMapping("/create")
    public CommonResult<Long> create(@Validated({StudentDomain.StudentCreate.class}) @RequestBody StudentDomain create) {
        return CommonResult.success(service.create(create));
    }

    @ApiOperation(value = "修改学生信息")
    @PostMapping("/update")
    public CommonResult<Void> update(@Validated({StudentDomain.StudentUpdate.class}) @RequestBody StudentDomain update) {
        service.update(update);
        return CommonResult.success();
    }

    @ApiOperation(value = "获取学生详情")
    @GetMapping("/get")
    public CommonResult<StudentDomain> get(@Positive(message = "学生Id格式错误") @RequestParam Long id) {
        return CommonResult.success(service.get(id));
    }

    @ApiOperation(value = "删除学生")
    @DeleteMapping("/delete")
    public CommonResult<Integer> delete(@Positive(message = "学生Id格式错误") @RequestParam Long id) {
        return CommonResult.success(service.delete(id));
    }

    @ApiOperation(value = "学生列表")
    @PostMapping("/list")
    public CommonResult<PageResult<StudentDomain>> list(@Valid @RequestBody StudentPage page) {
        return CommonResult.success(service.list(page));
    }
}
