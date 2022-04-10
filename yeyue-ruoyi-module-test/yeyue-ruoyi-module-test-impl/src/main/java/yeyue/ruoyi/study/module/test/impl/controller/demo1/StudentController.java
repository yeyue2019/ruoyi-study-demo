package yeyue.ruoyi.study.module.test.impl.controller.demo1;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.*;
import yeyue.ruoyi.study.module.test.api.domain.demo1.Student;
import yeyue.ruoyi.study.module.test.api.service.demo1.StudentService;
import yeyue.ruoyi.study.module.test.api.service.demo1.dto.StudentPage;

import javax.annotation.Resource;

/**
 * @author yeyue
 * @date 2022-04-10 14:39:51
 */
@Api(tags = "学生管理")
@RestController
@RequestMapping("/test/demo1/student")
public class StudentController {

    @Resource
    StudentService service;

    @ApiOperation(value = "增加学生")
    @PutMapping("/create")
    public CommonResult<Long> create(@RequestBody Student create) {
        return CommonResult.success(service.create(create));
    }

    @ApiOperation(value = "修改学生信息")
    @PostMapping("/update")
    public CommonResult<Void> update(@RequestBody Student update) {
        service.update(update);
        return CommonResult.success();
    }

    @ApiOperation(value = "获取学生详情")
    @GetMapping("/get")
    public CommonResult<Student> get(@RequestParam Long id) {
        return CommonResult.success(service.get(id));
    }

    @ApiOperation(value = "删除学生")
    @DeleteMapping("/delete")
    public CommonResult<Integer> delete(@RequestParam Long id) {
        return CommonResult.success(service.delete(id));
    }

    @ApiOperation(value = "学生列表")
    @PostMapping("/list")
    public CommonResult<PageResult<Student>> list(@RequestBody StudentPage page) {
        return CommonResult.success(service.list(page));
    }
}
