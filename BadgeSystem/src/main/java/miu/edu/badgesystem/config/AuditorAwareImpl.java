package miu.edu.badgesystem.config;

import miu.edu.badgesystem.util.SecurityContextUtil;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Long aLong=SecurityContextUtil.getLoggedInUserId();
        return Optional.ofNullable(SecurityContextUtil.getLoggedInUserId());
    }
}
