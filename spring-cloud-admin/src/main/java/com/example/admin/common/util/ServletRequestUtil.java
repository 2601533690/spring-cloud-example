package com.example.admin.common.util;

import javax.servlet.http.HttpServletRequest;

public class ServletRequestUtil {

    private static final String KEY_USER_AGENT = "User-Agent";

    public static String getUserAgent(HttpServletRequest request) {

        return request.getHeader(KEY_USER_AGENT);
    }

    public static String getDevice(HttpServletRequest request) {

        String userAgent = request.getHeader(KEY_USER_AGENT);
        String lowerCaseUserAgent = userAgent.toLowerCase();
        String os;
        String browser;

        //get os type by keywords
        if (lowerCaseUserAgent.contains("windows")) {

            os = "Windows";
        } else if (lowerCaseUserAgent.contains("mac")) {

            os = "Mac";
        } else if (lowerCaseUserAgent.contains("x11")) {

            os = "Unix";
        } else if (lowerCaseUserAgent.contains("android")) {

            os = "Android";
        } else if (lowerCaseUserAgent.contains("iphone")) {

            os = "iPhone";
        } else {

            os = "UnKnown";
        }

        //get browser type by keywords
        if (lowerCaseUserAgent.contains("edge")) {

            browser = "Edge";
        } else if (lowerCaseUserAgent.contains("msie")) {

            browser = "MSIE";
        } else if (lowerCaseUserAgent.contains("opr")) {

            browser = "OPR";
        } else if (lowerCaseUserAgent.contains("opera")) {

            browser = "Opera";
        } else if (lowerCaseUserAgent.contains("chrome")) {

            browser = "Chrome";
        } else if (lowerCaseUserAgent.contains("safari")) {

            browser = "Safari";
        } else if (lowerCaseUserAgent.contains("mozilla/7.0")
                || lowerCaseUserAgent.contains("netscape6")
                || lowerCaseUserAgent.contains("mozilla/4.7")
                || lowerCaseUserAgent.contains("mozilla/4.78")
                || lowerCaseUserAgent.contains("mozilla/4.08")
                || lowerCaseUserAgent.contains("mozilla/3")) {

            browser = "Netscape-?";
        } else if (lowerCaseUserAgent.contains("firefox")) {

            browser = "Firefox";
        } else if (lowerCaseUserAgent.contains("rv")) {

            browser = "IE";
        } else {

            browser = "UnKnown";
        }
        return os + " " + browser;
    }

    public static String getClientIp(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("Proxy-Client-IP");

            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {

            String[] ips = ip.split(",");

            for (String ipInLoop : ips) {

                if (!("unknown".equalsIgnoreCase(ipInLoop))) {

                    ip = ipInLoop;
                    break;
                }
            }
        }
        return ip;
    }

}
