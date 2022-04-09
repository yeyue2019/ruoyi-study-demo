package yeyue.ruoyi.study.framework.mybatis.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import yeyue.ruoyi.study.framework.mybatis.core.entity.MyBatisEntity;

import java.time.LocalDateTime;
import java.util.Objects;

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
            MyBatisEntity entity = (MyBatisEntity) metaObject.getOriginalObject();
            LocalDateTime current = LocalDateTime.now();
            // 创建时间为空，则以当前时间为插入时间
            if (Objects.isNull(entity.getCreateTime())) {
                entity.setCreateTime(current);
            }
            // 更新时间为空，则以当前时间为更新时间
            if (Objects.isNull(entity.getUpdateTime())) {
                entity.setUpdateTime(current);
            }
            // TODO: 2022/4/9 操作者字段后续补充
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof MyBatisEntity) {
            MyBatisEntity entity = (MyBatisEntity) metaObject.getOriginalObject();
            LocalDateTime current = LocalDateTime.now();
            entity.setUpdateTime(current);
            // TODO: 2022/4/9 操作者字段后续补充
        }
    }
}
