package org.CRUD.Learning.utils;

import org.CRUD.Learning.annotation.MaskingStrategy;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class MaskingUtil {

    public static String mask(String value, MaskingStrategy strategy) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Boolean shouldMask = (Boolean) request.getAttribute("shouldMask");

            System.out.println("===="+shouldMask);
        // Check if masking should be applied based on interceptor decision
        if (shouldMask != null && !shouldMask) {
            return value; // Don't mask if shouldMask is false
        }

        // Apply masking based on strategy
        switch (strategy) {
            case EMAIL:
                return maskEmail(value);
            case PHONE:
                return maskPhone(value);
            // Add more cases as needed
            default:
                return value;
        }
    }

    private static String maskEmail(String email) {
        int atIndex = email.indexOf('@');
        if (atIndex > 0) {
            String maskedPart = email.substring(0, atIndex).replaceAll(".", "*");
            return maskedPart + email.substring(atIndex);
        }
        return email;
    }

    private static String maskPhone(String phone) {
        // Example masking for phone number
        return phone.replaceAll("\\d(?=\\d{4})", "*");
    }
}
