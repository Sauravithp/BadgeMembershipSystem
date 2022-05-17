package miu.edu.badgesystem.util;//package miu.edu.badgesystem.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextUtil {
    public static Long getLoggedInUserId() {

        Object s=SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return 1L;
    }

}
