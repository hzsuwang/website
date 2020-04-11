package com.yyq.iterror.util;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class  UploadUtil {

    private static Logger logger = LoggerFactory.getLogger(UploadUtil.class);
    public static int maxlength = 5000;
    public static final int CODE_UPLOAD_FILE_ERROR = -800101;
    public static final int RETURN_RESULT_SUCCESS = 1;

    /**
     * 上传失败
     * @param response
     */
    public static void updateFailed(HttpServletResponse response) {
        updateFailed(response, "上传图片失败，请稍后再试");
    }

    /**
     * 上传失败
     * @param response
     */
    public static void updateFailed(HttpServletResponse response, String msg) {
        JSONObject jsonResult = new JSONObject();
        jsonResult.put("rc", CODE_UPLOAD_FILE_ERROR);
        jsonResult.put("msg", msg);
        try {
            response.getWriter().write(jsonResult.toString());
        } catch (IOException e) {
            logger.error("response error,", e);
        }
    }

    /**
     * 上传成功
     * @param response
     * @param url
     */
    public static void updateSuccess(HttpServletResponse response, String url) {
        JSONObject jsonResult = new JSONObject();
        jsonResult.put("rc", RETURN_RESULT_SUCCESS);
        JSONObject data = new JSONObject();
        data.put("url", url);
        jsonResult.put("data", data);
        try {
            response.getWriter().write(jsonResult.toString());
        } catch (IOException e) {
            logger.error("response error,", e);
        }
    }

    /**
     * 把文件写到磁盘
     * @param inputStream
     * @param file
     * @return
     */
    public synchronized static boolean writeFileToDisk(InputStream inputStream, File file) {
        FileOutputStream fos = null;
        boolean result = false;
        try {
            fos = new FileOutputStream(file);
            byte[] b = new byte[1024];
            int bytelen = 0;
            while (-1 != (bytelen = inputStream.read(b))) {
                fos.write(b, 0, bytelen);
                result = true;
            }
        } catch (FileNotFoundException e) {
            result = false;
        } catch (IOException e) {
            result = false;
        } finally {
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                result = false;
            }
            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                result = false;
            }
        }
        return result;
    }
}

