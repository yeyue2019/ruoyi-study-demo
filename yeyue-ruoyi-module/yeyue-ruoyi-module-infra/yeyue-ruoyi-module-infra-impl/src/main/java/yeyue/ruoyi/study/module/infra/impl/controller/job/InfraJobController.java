package yeyue.ruoyi.study.module.infra.impl.controller.job;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.module.infra.api.domain.job.InfraJobDomain;
import yeyue.ruoyi.study.module.infra.api.service.job.InfraJobService;
import yeyue.ruoyi.study.module.infra.api.service.job.dto.InfraJobCreateReqDTO;
import yeyue.ruoyi.study.module.infra.api.service.job.dto.InfraJobPageReqDTO;
import yeyue.ruoyi.study.module.infra.api.service.job.dto.InfraJobStatusUpdateReqDTO;
import yeyue.ruoyi.study.module.infra.api.service.job.dto.InfraJobUpdateReqDTO;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author yeyue
 * @date 2022-06-06 17:11:46
 */
@Api(tags = "定时任务")
@RestController
@RequestMapping("/web/infra/job")
public class InfraJobController {

    @Resource
    InfraJobService service;

    @PutMapping("/create")
    @ApiOperation("创建定时任务")
    public CommonResult<Long> create(@Valid @RequestBody InfraJobCreateReqDTO reqDTO) {
        return CommonResult.success(service.create(reqDTO));
    }

    @PostMapping("/update")
    @ApiOperation("更新定时任务")
    public CommonResult<Void> update(@Valid @RequestBody InfraJobUpdateReqDTO reqDTO) {
        service.update(reqDTO);
        return CommonResult.success();
    }

    @PostMapping("/update-status")
    @ApiOperation("更新任务状态")
    public CommonResult<Void> updateStatus(@Valid @RequestBody InfraJobStatusUpdateReqDTO reqDTO) {
        service.updateStatus(reqDTO);
        return CommonResult.success();
    }

    @GetMapping("/get")
    @ApiOperation("获取任务详情")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    public CommonResult<InfraJobDomain> get(@RequestParam Long id) {
        return CommonResult.success(service.get(id));
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除定时任务")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    public CommonResult<Void> delete(@RequestParam Long id) {
        service.delete(id);
        return CommonResult.success();
    }

    @GetMapping("/trigger")
    @ApiOperation("触发定时任务")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    public CommonResult<Void> trigger(@RequestParam Long id) {
        service.trigger(id);
        return CommonResult.success();
    }

    @PostMapping("/list")
    @ApiOperation("定时任务列表")
    public CommonResult<PageResult<InfraJobDomain>> list(@Valid @RequestBody InfraJobPageReqDTO reqDTO) {
        return CommonResult.success(service.list(reqDTO));
    }
}
