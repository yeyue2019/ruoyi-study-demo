package yeyue.ruoyi.study.validation.dto;

import lombok.Data;

import java.time.*;

/**
 * @author yeyue
 * @date 2022-04-09 17:50:40
 */
@Data
public class UserVO {

    private Long id;

    private String name;

    private GenderEnum gender;

    private LocalDateTime birthday;
}
