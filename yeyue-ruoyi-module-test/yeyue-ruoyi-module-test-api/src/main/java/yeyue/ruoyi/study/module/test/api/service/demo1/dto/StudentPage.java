package yeyue.ruoyi.study.module.test.api.service.demo1.dto;

import io.swagger.annotations.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.pojo.PageParam;
import yeyue.ruoyi.study.framework.validation.annotation.InEnum;
import yeyue.ruoyi.study.module.test.api.enums.GenderEnum;

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
    private String nameLike;

    @ApiModelProperty(value = "手机号查找")
    private String mobileEq;

    @ApiModelProperty(value = "性别筛选")
    @InEnum(value = GenderEnum.class, message = "性别不在可选氛围内:%s")
    private String genderEq;

    @ApiModelProperty(value = "添加开始时间")
    private LocalDateTime createTimeStart;

    @ApiModelProperty(value = "添加结束时间")
    private LocalDateTime createTimeEnd;
}
