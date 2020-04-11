package com.yyq.iterror.common;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
public class BaseController {

    /**
     * 读取请求内容
     * 
     * @param request
     * @return
     */
    private String getRequestContent(HttpServletRequest request) {
        StringBuilder buffer = new StringBuilder();
        InputStream is = null;
        try {
            is = request.getInputStream();
            String content = "";
            if (is != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                while ((content = reader.readLine()) != null) {
                    buffer.append(content);
                }
            }
        } catch (IOException e) {

        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    // do noting
                }
            }
        }
        return buffer.toString();
    }
}
