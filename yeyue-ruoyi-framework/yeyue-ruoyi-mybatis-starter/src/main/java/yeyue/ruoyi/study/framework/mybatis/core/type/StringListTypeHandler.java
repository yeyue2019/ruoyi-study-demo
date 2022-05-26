package yeyue.ruoyi.study.framework.mybatis.core.type;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import yeyue.ruoyi.study.framework.common.constants.StringConstants;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义类型转换器 List<String> <-> String
 *
 * @author yeyue
 * @date 2022-04-18 14:58:03
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(List.class)
public class StringListTypeHandler implements TypeHandler<List<String>> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, List<String> strings, JdbcType jdbcType)
            throws SQLException {
        preparedStatement.setString(i,
                strings == null ? null : StringUtils.joinWith(StringConstants.SPLIT_JOIN, strings.toArray(new Object[0])));
    }

    @Override
    public List<String> getResult(ResultSet resultSet, String s) throws SQLException {
        String value = resultSet.getString(s);
        return getResult(value);
    }

    @Override
    public List<String> getResult(ResultSet resultSet, int i) throws SQLException {
        String value = resultSet.getString(i);
        return getResult(value);
    }

    @Override
    public List<String> getResult(CallableStatement callableStatement, int i) throws SQLException {
        String value = callableStatement.getString(i);
        return getResult(value);
    }

    private List<String> getResult(String value) {
        if (value == null) {
            return null;
        }
        return Arrays.asList(StringUtils.split(value, StringConstants.SPLIT_JOIN));
    }
}
