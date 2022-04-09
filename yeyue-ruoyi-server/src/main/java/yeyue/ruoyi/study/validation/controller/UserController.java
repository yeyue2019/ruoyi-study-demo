package yeyue.ruoyi.study.validation.controller;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.validation.dto.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Slf4j
@RestController
@RequestMapping("/users")
@Validated
@Api("用户Controller")
public class UserController {

    @ApiOperation("获取用户信息")
    @GetMapping("/get")
    public void get(@RequestParam("id") @Min(value = 1L, message = "编号必须大于 0") Integer id) {
        log.info("[get][id: {}]", id);
    }

    @ApiOperation("增加信息")
    @PostMapping("/add")
    public void add(@Valid UserAddDTO addDTO) {
        log.info("[add][addDTO: {}]", addDTO);
    }

    @PostMapping("/update_gender")
    @ApiOperation("更新用户")
    public void updateGender(@Valid UserUpdateGenderDTO updateGenderDTO) {
        log.info("[updateGender][updateGenderDTO: {}]", updateGenderDTO);
    }

    @PostMapping("/update_status_true")
    @ApiOperation("更新用户2")
    public void updateStatusTrue(@Validated(UserUpdateStatusDTO.Group01.class) UserUpdateStatusDTO updateStatusDTO) {
        log.info("[updateStatusTrue][updateStatusDTO: {}]", updateStatusDTO);
    }

    @PostMapping("/update_status_false")
    @ApiOperation("更新用户3")
    public void updateStatusFalse(@Validated(UserUpdateStatusDTO.Group02.class) UserUpdateStatusDTO updateStatusDTO) {
        log.info("[updateStatusFalse][updateStatusDTO: {}]", updateStatusDTO);
    }

    @PostMapping("/update")
    @ApiOperation("更新用户4")
    public void update(@Valid UserUpdateDTO updateDTO) {
        log.info("[update][updateDTO: {}]", updateDTO);
    }

}
