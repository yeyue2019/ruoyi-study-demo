package yeyue.ruoyi.study.framework.common.pojo.http;

import io.swagger.annotations.*;
import lombok.*;
import yeyue.ruoyi.study.framework.common.util.servlet.ServletUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

/**
 * Http请求[不包含文件上传]
 *
 * @author yeyue
 * @date 2022-04-15 12:18:39
 */
@Data
@ApiModel
@NoArgsConstructor
public class HttpRequest implements Serializable {

    @ApiModelProperty(value = "请求的完整URL")
    private String url;

    @ApiModelProperty(value = "请求方法")
    private String method;

    @ApiModelProperty(value = "请求参数")
    private Map<String, String[]> params;

    @ApiModelProperty(value = "请求头")
    private Map<String, String> headers;

    @ApiModelProperty(value = "请求体")
    private String body;

    public HttpRequest(HttpServletRequest request) {
        this.url = ServletUtils.getUrl(request);
        this.method = ServletUtils.getMethod(request);
        this.params = ServletUtils.getParams(request);
        this.headers = ServletUtils.getHeaderMap(request);
        this.body = ServletUtils.getBodyString(request);
    }

    public HttpRequest(String url, String method, Map<String, String[]> params, Map<String, String> headers, String body) {
        this.url = url;
        this.method = method;
        this.params = params;
        this.headers = headers;
        this.body = body;
    }

    public HttpRequest(String url, String method, Map<String, String[]> params, Map<String, String> headers) {
        this(url, method, params, headers, null);
    }

    public HttpRequest(String url, String method, Map<String, String> headers, String body) {
        this(url, method, new HashMap<>(0), headers, body);
    }

    public HttpRequest(String url, String method, Map<String, String> headers) {
        this(url, method, new HashMap<>(0), headers, null);
    }
}
