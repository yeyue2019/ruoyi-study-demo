package yeyue.ruoyi.study.module.test.impl.demo1.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.mybatis.core.entity.MyBatisEntity;
import yeyue.ruoyi.study.module.test.api.demo1.enums.GenderEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 学生实体
 *
 * @author yeyue
 * @date 2022-04-10 18:31:18
 */
@Data
@TableName("test_student")
public class StudentEntity extends MyBatisEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String name;

    private String idCard;

    private String mobile;

    private LocalDate birthday;

    @EnumValue
    private GenderEnum gender;

    private Boolean graduate;

    private BigDecimal grade;

    private Integer awardNumber;

    private String description;
}
