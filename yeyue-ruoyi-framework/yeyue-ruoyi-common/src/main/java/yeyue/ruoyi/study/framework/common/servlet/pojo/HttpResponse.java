package yeyue.ruoyi.study.framework.common.servlet.pojo;

import com.alibaba.fastjson.*;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * Http请求结果[不包含文件下载]
 *
 * @author yeyue
 * @date 2022-04-15 13:11:44
 */
@Data
@ApiModel
@NoArgsConstructor
public class HttpResponse implements Serializable {

    @ApiModelProperty(value = "http状态码")
    private int status;

    @ApiModelProperty(value = "错误提示信息")
    private String message;

    @ApiModelProperty(value = "响应体")
    private String body;

    @ApiModelProperty(value = "响应头")
    private Map<String, String> headers;

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    @ApiModelProperty(hidden = true)
    public boolean isSuccess() {
        return this.status >= 200 && this.status < 300;
    }

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    @ApiModelProperty(hidden = true)
    public boolean isNotSuccess() {
        return !isSuccess();
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("status", this.status);
        json.put("message", this.message);
        json.put("headers", this.headers);
        json.put("body", JSONValidator
                .from(this.body)
                .validate() ? JSON.parse(this.body) : body);
        return json.toJSONString();
    }
}
