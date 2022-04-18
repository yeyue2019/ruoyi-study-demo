package yeyue.ruoyi.study.module.test.api.demo1.service.dto;

import io.swagger.annotations.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageParam;
import yeyue.ruoyi.study.framework.common.validation.annotation.InStringEnum;
import yeyue.ruoyi.study.module.test.api.demo1.enums.GenderEnum;

import java.time.LocalDateTime;

/**
 * 学生分页查询
 *
 * @author yeyue
 * @date 2022-04-10 12:24:04
 */
@Data
@ApiModel(description = "分页查询学生信息")
public class StudentPage extends PageParam {

    @ApiModelProperty(value = "名称模糊匹配")
    private String name;

    @ApiModelProperty(value = "手机号查找")
    private String mobile;

    @ApiModelProperty(value = "性别筛选")
    @InStringEnum(value = GenderEnum.class, message = "性别不在可选氛围内:%s")
    private String gender;

    @ApiModelProperty(value = "添加开始时间")
    private LocalDateTime createTimeStart;

    @ApiModelProperty(value = "添加结束时间")
    private LocalDateTime createTimeEnd;
}
