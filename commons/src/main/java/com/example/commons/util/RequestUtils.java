package com.example.commons.util;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Utility class for request-related operations.
 */
public class RequestUtils {

    /**
     * Gets the client IP address from the request.
     * 
     * @param request the HTTP servlet request
     * @return the client IP address
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }
        
        String xRealIp = request.getHeader("X-Real-IP");
        if (xRealIp != null && !xRealIp.isEmpty()) {
            return xRealIp;
        }
        
        return request.getRemoteAddr();
    }

    /**
     * Checks if the request is from a local address.
     * 
     * @param request the HTTP servlet request
     * @return true if the request is from localhost
     */
    public static boolean isLocalRequest(HttpServletRequest request) {
        String ip = getClientIpAddress(request);
        return "127.0.0.1".equals(ip) || "localhost".equals(ip) || "::1".equals(ip);
    }

    /**
     * Gets a formatted request summary string.
     * 
     * @param request the HTTP servlet request
     * @return formatted request summary
     */
    public static String getRequestSummary(HttpServletRequest request) {
        return String.format("%s %s [%s]", 
            request.getMethod(), 
            request.getRequestURI(),
            getClientIpAddress(request));
    }
}

