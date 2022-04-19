package yeyue.ruoyi.study.framework.common.servlet.wrapper;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/**
 * @author yeyue
 * @date 2022-04-15 17:38:55
 */
public class HttpResponseCopyWrapper extends HttpServletResponseWrapper {

    private final ByteArrayOutputStream byteArrayOutputStream;
    private final ServletOutputStream servletOutputStream;

    public HttpResponseCopyWrapper(HttpServletResponse response) {
        super(response);
        byteArrayOutputStream = new ByteArrayOutputStream();
        servletOutputStream = new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {

            }

            @Override
            public void write(int b) throws IOException {
                response.getOutputStream().write(b);
                byteArrayOutputStream.write(b);
            }
        };
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return servletOutputStream;
    }

    public byte[] toByteArray() {
        return byteArrayOutputStream.toByteArray();
    }
}
