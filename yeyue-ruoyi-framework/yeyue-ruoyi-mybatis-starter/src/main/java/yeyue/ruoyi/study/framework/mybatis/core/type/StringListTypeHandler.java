package yeyue.ruoyi.study.framework.mybatis.core.type;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.*;
import yeyue.ruoyi.study.framework.common.constants.CommonConstants;

import java.sql.*;
import java.util.*;

/**
 * 自定义类型转换器 List<String>  <-> String
 *
 * @author yeyue
 * @date 2022-04-18 14:58:03
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(List.class)
public class StringListTypeHandler implements TypeHandler<List<String>> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, List<String> strings, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, strings == null ? null : StringUtils.joinWith(CommonConstants.SPLIT_JOIN, strings.toArray(new Object[0])));
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
        return Arrays.asList(StringUtils.split(value, CommonConstants.SPLIT_JOIN));
    }
}