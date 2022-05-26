package yeyue.ruoyi.study.framework.mybatis.core.type;

import java.util.Set;

import org.apache.ibatis.type.*;

import com.alibaba.fastjson.*;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;

/**
 * 自定义类型转换器 JSON <-> Set<Long>
 *
 * @author yeyue
 * @date 2022-04-18 14:53:11
 */
@MappedTypes({Object.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class JsonLongSetTypeHandler extends AbstractJsonTypeHandler<Object> {

    private static final TypeReference<Set<Long>> TYPE_REFERENCE = new TypeReference<Set<Long>>() {};

    @Override
    protected Object parse(String json) {
        return JSONObject.parseObject(json, TYPE_REFERENCE);
    }

    @Override
    protected String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }
}
