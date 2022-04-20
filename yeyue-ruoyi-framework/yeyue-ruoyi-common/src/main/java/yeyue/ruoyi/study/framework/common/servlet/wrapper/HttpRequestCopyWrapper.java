package yeyue.ruoyi.study.framework.common.servlet.wrapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.exception.common.GlobalErrorCode;
import yeyue.ruoyi.study.framework.common.servlet.util.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.charset.Charset;

/**
 * 请求拷贝类
 *
 * @author yeyue
 * @date 2022-04-14 20:31:46
 */
@Slf4j
public class HttpRequestCopyWrapper extends HttpServletRequestWrapper {

    /**
     * 缓存的内容
     */
    private final byte[] body;

    public HttpRequestCopyWrapper(HttpServletRequest request) {
        super(request);
        String encode = null;
        try {
            if (StringUtils.isEmpty((encode = request.getCharacterEncoding()))) {
                request.setCharacterEncoding((encode = Charset.defaultCharset().displayName()));
            }
            this.body = ServletUtils.getBodyString(request, encode).getBytes(encode);
        } catch (UnsupportedEncodingException e) {
            log.warn("当前系统不支持该系统编码:{}", encode);
            throw new ServiceException(GlobalErrorCode.SYSTEM_CODE_SUPPORT_ERROR);
        }
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(body);
        // 返回 ServletInputStream
        return new ServletInputStream() {

            @Override
            public int read() {
                return inputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public int available() {
                return body.length;
            }

        };
    }
}
