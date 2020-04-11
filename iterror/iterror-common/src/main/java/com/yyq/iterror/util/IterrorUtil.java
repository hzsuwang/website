package com.yyq.iterror.util;

import org.apache.commons.lang3.StringUtils;

public class IterrorUtil {

    /**
     * 截取到host地扯
     * 
     * @param url
     * @return
     */
    public static String subHost(String url) {
        if (url != null && url.startsWith("http://")) {
            url = url.substring(url.indexOf("//") + 2).substring(url.substring(url.indexOf("//") + 2).indexOf("/"));
        }
        if (url != null && url.startsWith("https://")) {
            url = url.substring(url.indexOf("//") + 2).substring(url.substring(url.indexOf("//") + 2).indexOf("/"));
        }
        return url;
    }

    /**
     * @param host
     * @param url
     * @return
     */
    public static String addHost(String host, String url) {
        if (StringUtils.isNotBlank(url)) {
            String urlPrefix = subHost(url);
            return host + urlPrefix;
        }
        return null;
    }


}
