package yeyue.ruoyi.study.framework.mybatis.core.injector;

import com.baomidou.mybatisplus.core.injector.*;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import yeyue.ruoyi.study.framework.mybatis.core.injector.method.UpdateBatchColumnByIds;

import java.util.List;

/**
 * 添加mybatis对于mysql批量操作的支持
 *
 * @author yeyue
 * @date 2022-04-09 23:33:33
 */
public class MyBatisSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methods = super.getMethodList(mapperClass, tableInfo);
        methods.add(new InsertBatchSomeColumn());
        methods.add(new UpdateBatchColumnByIds());
        return methods;
    }
}
