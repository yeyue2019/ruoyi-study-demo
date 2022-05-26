package yeyue.ruoyi.study.framework.mybatis.core.handler;

import java.time.LocalDateTime;
import java.util.Objects;

import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import yeyue.ruoyi.study.framework.common.servlet.security.WebSecurityUtils;
import yeyue.ruoyi.study.framework.mybatis.core.entity.MyBatisEntity;

/**
 * 字段自动填充处理器
 *
 * @author yeyue
 * @date 2022-04-09 23:10:05
 */
public class MyBatisFieldAutoFillHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof MyBatisEntity) {
            MyBatisEntity entity = (MyBatisEntity)metaObject.getOriginalObject();
            LocalDateTime current = LocalDateTime.now();
            if (Objects.isNull(entity.getCreateTime())) {
                entity.setCreateTime(current);
            }
            if (Objects.isNull(entity.getUpdateTime())) {
                entity.setUpdateTime(current);
            }
            if (Objects.isNull(entity.getCreator())) {
                entity.setCreator(WebSecurityUtils.getLoginUserId());
            }
            if (Objects.isNull(entity.getUpdater())) {
                entity.setUpdater(WebSecurityUtils.getLoginUserId());
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof MyBatisEntity) {
            MyBatisEntity entity = (MyBatisEntity)metaObject.getOriginalObject();
            LocalDateTime current = LocalDateTime.now();
            entity.setUpdateTime(current);
            entity.setUpdater(WebSecurityUtils.getLoginUserId());
        }
    }
}
