package yeyue.ruoyi.study.framework.mybatis.core.injector.method;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 根据id更新字段
 *
 * @author yeyue
 * @date 2022-05-12 15:26:33
 */
public class UpdateBatchColumnByIds extends AbstractMethod {

    // LOGIC_DELETE_BATCH_BY_IDS "<script>\nUPDATE %s %s WHERE %s IN (%s) %s\n</script>"
    // UPDATE_BY_ID "<script>\nUPDATE %s %s WHERE %s=#{%s} %s\n</script>"

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.LOGIC_DELETE_BATCH_BY_IDS;
        final String additional = optlockVersion(tableInfo) + tableInfo.getLogicDeleteSql(true, true);
        String sql = String.format(
                sqlMethod.getSql(),
                tableInfo.getTableName(),
                sqlSet(tableInfo.isWithLogicDelete(), false, tableInfo, false, ENTITY, ENTITY_DOT),
                tableInfo.getKeyColumn(),
                SqlScriptUtils.convertForeach(
                        SqlScriptUtils.convertChoose("@org.apache.ibatis.type.SimpleTypeRegistry@isSimpleType(item.getClass())",
                                "#{item}", "#{item." + tableInfo.getKeyProperty() + "}"),
                        COLLECTION, null, "item", COMMA),
                additional
        );
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, Object.class);
        return addUpdateMappedStatement(mapperClass, modelClass, getMethod(sqlMethod), sqlSource);
    }

    /**
     * 自定义方法名
     */
    @Override
    public String getMethod(SqlMethod sqlMethod) {
        return "updateBatchColumnByIds";
    }
}
