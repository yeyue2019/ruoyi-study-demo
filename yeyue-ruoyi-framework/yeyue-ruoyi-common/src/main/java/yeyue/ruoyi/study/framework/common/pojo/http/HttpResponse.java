package yeyue.ruoyi.study.framework.common.pojo.http;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.*;
import lombok.*;

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

    public HttpResponse(int status, String message, String body, Map<String, String> headers) {
        this.status = status;
        this.message = message;
        this.body = body;
        this.headers = headers;
    }

    public HttpResponse(int status, String body, Map<String, String> headers) {
        this(status, null, body, headers);
    }

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

}
