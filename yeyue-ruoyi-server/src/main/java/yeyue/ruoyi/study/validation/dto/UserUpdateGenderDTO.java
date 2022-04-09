package yeyue.ruoyi.study.validation.dto;

import lombok.Data;
import yeyue.ruoyi.study.framework.validation.annotation.InEnum;

import javax.validation.constraints.NotNull;

/**
 * 用户更新性别 DTO
 */
@Data
public class UserUpdateGenderDTO {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Integer id;

    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
    @InEnum(value = GenderEnum.class, message = "性别必须是 %s")
    private String gender;

    public Integer getId() {
        return id;
    }

    public UserUpdateGenderDTO setId(Integer id) {
        this.id = id;
        return this;
    }


}
