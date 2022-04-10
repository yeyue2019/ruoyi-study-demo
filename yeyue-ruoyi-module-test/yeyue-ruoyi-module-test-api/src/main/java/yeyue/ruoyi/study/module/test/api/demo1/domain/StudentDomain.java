package yeyue.ruoyi.study.module.test.api.demo1.domain;

import io.swagger.annotations.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.validation.annotation.InEnum;
import yeyue.ruoyi.study.module.test.api.demo1.enums.GenderEnum;

import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.*;

/**
 * 学生
 *
 * @author yeyue
 * @date 2022-04-10 11:25:26
 */
@Data
@ApiModel(description = "学生领域对象")
public class StudentDomain implements Serializable {

    public interface StudentCreate extends Default {
    }

    public interface StudentUpdate extends Default {

    }

    @ApiModelProperty(value = "学生编号")
    @NotNull(groups = StudentUpdate.class, message = "学生Id不能为空")
    @Null(groups = StudentCreate.class, message = "学生Id不能传值")
    private Long id;

    @ApiModelProperty(value = "学生姓名")
    @NotEmpty(message = "学生姓名不能为空")
    private String name;

    @ApiModelProperty(value = "身份证号")
    @NotEmpty(message = "身份证号不能为空")
    @Size(min = 18, max = 18, message = "身份证号长度错误")
    private String idCard;

    @ApiModelProperty(value = "手机号")
    @NotEmpty(message = "手机号不能为空")
    @Size(min = 11, max = 11, message = "手机号长度错误")
    private String mobile;

    @ApiModelProperty(value = "出生日期")
    @NotNull(message = "生日信息不能为空")
    private LocalDate birthday;

    @ApiModelProperty(value = "性别")
    @InEnum(value = GenderEnum.class, message = "性别必须在%s中填写")
    private String gender;

    @ApiModelProperty(value = "是否完成学业")
    @NotNull(message = "学生学业完成情况不能为空")
    private Boolean graduate;

    @ApiModelProperty(value = "入校考核分数")
    @NotNull(message = "学生成绩不能为空")
    @DecimalMin(value = "0.00", message = "学生入校成绩不能低于下限")
    @DecimalMax(value = "100.00", message = "学生入校成绩不能超出上限")
    private BigDecimal grade;

    @ApiModelProperty(value = "在校获奖次数")
    @NotNull(message = "学生在校获奖次数不能为空")
    @PositiveOrZero(message = "学生获奖次数不可为负")
    private Integer awardNumber;

    @ApiModelProperty(value = "备注")
    private String description;

    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;
}
