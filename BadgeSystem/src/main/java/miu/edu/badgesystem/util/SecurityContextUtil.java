package miu.edu.badgesystem.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextUtil {
    public static Long getLoggedInUserId() {
        return (Long) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }

}
