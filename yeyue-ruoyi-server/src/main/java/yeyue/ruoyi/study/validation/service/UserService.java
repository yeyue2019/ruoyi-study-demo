package yeyue.ruoyi.study.validation.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import yeyue.ruoyi.study.validation.dto.UserAddDTO;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Slf4j
@Service
@Validated
public class UserService {

    public void get(@Min(value = 1L, message = "编号必须大于 0") Integer id) {
        log.info("[get][id: {}]", id);
    }

    public void add(@Valid UserAddDTO addDTO) {
        log.info("[add][addDTO: {}]", addDTO);
    }

    public void add01(UserAddDTO addDTO) {
        this.add(addDTO);
    }

    public void add02(UserAddDTO addDTO) {
        self().add(addDTO);
    }

    private UserService self() {
        return (UserService) AopContext.currentProxy();
    }

}
