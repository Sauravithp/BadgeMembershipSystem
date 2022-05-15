package miu.edu.badgesystem.security.filter;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.http.HttpRequest;

@Component
public class JwtFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String url = String.valueOf(httpRequest.getRequestURL());
        if (url.contains("/login")) {
            chain.doFilter(request, response);
        } else {
            String header=httpRequest.getHeader("Authorization");
            if(ObjectUtils.isEmpty(header)){
                throw new NullPointerException("Error");
            }else {
                chain.doFilter(request,response);
            }
        }

    }

}
