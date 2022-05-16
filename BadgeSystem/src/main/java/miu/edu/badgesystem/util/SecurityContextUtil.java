package miu.edu.badgesystem.util;

import miu.edu.badgesystem.service.impl.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextUtil {
    public static Long getLoggedInUserId() {
        CustomUserDetails s1=(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return s1.getId();
    }

}
