package com.yyq.iterror.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ImgUtil {

    private static MultiThreadedHttpConnectionManager connectionManager;


    public static File getLocalFile(String url, String extname) {
        String filePath = FileUtil.createLocalFilePath(FileUtil.createFileNewName(extname));
        boolean result = getImgByUrl(url, filePath);
        if (result) {
            return new File(filePath);
        }
        return null;
    }

    /**
     * 保存一张源图
     *
     * @param url
     * @param localFilePath
     * @return
     */
    public static boolean getImgByUrl(String url, String localFilePath) {
        boolean result = false;
        Map<String, Object> response = get(url);
        if (response == null) {
            return result;
        }
        byte[] resBytes = (byte[]) response.get("responseBody");
        try {
            // 源图片保存
            FileOutputStream fileOutputStream = new FileOutputStream(new File(localFilePath));
            fileOutputStream.write(resBytes);
            fileOutputStream.flush();
            fileOutputStream.close();
            result = true;
        } catch (Exception e) {
            log.error("getImgByUrl write file error: ", e);
        }
        return result;
    }

    /**
     * http get 请求
     * 
     * @param url
     * @return
     */
    private static Map<String, Object> get(String url) {
        GetMethod getmethod = new GetMethod(url);
        return httpRequest(getmethod, null, false);
    }

    /**
     * http get 请求
     * 
     * @param url
     * @param params
     * @param headers
     * @return {@link Map}
     */
    private static Map<String, Object> get(String url, PostParameter[] params, Map<String, String> headers) {

        if (null != params && params.length > 0) {
            String encodedParams = encodeParameters(params);
            if (-1 == url.indexOf("?")) {
                url += "?" + encodedParams;
            } else {
                url += "&" + encodedParams;
            }
        }
        GetMethod getmethod = new GetMethod(url);
        return httpRequest(getmethod, headers, true);
    }

    private static String encodeParameters(PostParameter[] postParams) {
        StringBuffer buf = new StringBuffer();
        for (int j = 0; j < postParams.length; j++) {
            if (j != 0) {
                buf.append("&");
            }
            try {
                if (postParams[j] == null || postParams[j].getName() == null) {
                    continue;
                }
                String value = postParams[j].getValue() == null ? "" : postParams[j].getValue();
                buf.append(URLEncoder.encode(postParams[j].getName(), "UTF-8")).append("=").append(URLEncoder.encode(value, "UTF-8"));
            } catch (java.io.UnsupportedEncodingException neverHappen) {
            }
        }
        return buf.toString();
    }

    private static HttpClient getHttpClient() {
        connectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = connectionManager.getParams();
        params.setDefaultMaxConnectionsPerHost(150);
        params.setConnectionTimeout(20000);
        params.setSoTimeout(20000);
        HttpClientParams clientParams = new HttpClientParams();
        clientParams.setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
        HttpClient client = new org.apache.commons.httpclient.HttpClient(clientParams, connectionManager);

        return client;
    }

    private static Map<String, Object> httpRequest(HttpMethod method, Map<String, String> specialHeaders, boolean resp2String) {
        // InetAddress ipaddr;
        HttpClient client = getHttpClient();
        int responseCode = -1;
        try {
            // ipaddr = InetAddress.getLocalHost();
            List<Header> headers = new ArrayList<Header>();
            if (specialHeaders != null) {
                // 特殊Http头，如微博请求时要带的accessToken等
                for (String key : specialHeaders.keySet()) {
                    headers.add(new Header(key, specialHeaders.get(key)));
                }
                client.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
            }

            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
            client.executeMethod(method);
            // Header[] resHeader = method.getResponseHeaders();
            responseCode = method.getStatusCode();

            Map<String, Object> response = new HashMap<String, Object>();
            if (responseCode == 200) {
                response.put("responseCode", Integer.toString(responseCode));
                if (resp2String) {
                    InputStream inputStream = method.getResponseBodyAsStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                    BufferedReader in = new BufferedReader(inputStreamReader);
                    String str;
                    StringBuilder sb = new StringBuilder();
                    while ((str = in.readLine()) != null) {
                        sb.append(str);
                    }
                    response.put("responseStr", sb.toString());
                    in.close();
                    inputStreamReader.close();
                    inputStream.close();
                } else {
                    byte[] responseBody = method.getResponseBody();
                    response.put("responseBody", responseBody);
                }
            } else {
                response.put("responseCode", Integer.toString(responseCode));
            }
            return response;
        } catch (IOException ioe) {
            // log.error("HTTP request error:",ioe);
            return null;
        } finally {
            method.releaseConnection();
        }
    }

    private static Map<String, String> httpRequest(HttpMethod method) {
        HttpClient client = getHttpClient();
        int responseCode = -1;
        try {
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
            client.executeMethod(method);
            // Header[] resHeader = method.getResponseHeaders();
            responseCode = method.getStatusCode();
            String responseStr = method.getResponseBodyAsString();

            Map<String, String> response = new HashMap<String, String>();
            if (responseCode == 200) {
                response.put("responseCode", Integer.toString(responseCode));
                response.put("responseStr", responseStr);
            }
            return response;
        } catch (IOException ioe) {
            log.error("HTTP request error: ", ioe);
            return null;
        } finally {
            method.releaseConnection();
        }
    }
}
