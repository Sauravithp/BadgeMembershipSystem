package miu.edu.transactionmanagementsystem.filter;

import lombok.SneakyThrows;
import miu.edu.transactionmanagementsystem.exception.ExceptionResponse;
import miu.edu.transactionmanagementsystem.util.ObjectMapperUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class JwtFilter implements Filter {


    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String url = String.valueOf(httpRequest.getRequestURL());
        if (url.contains("/login")) {
            chain.doFilter(request, response);
        } else {
//            String header = httpRequest.getHeader("Authorization");
//            try {
//                //header
//                //decrypt the jwt
//                header.contains("test");
//                chain.doFilter(request, response);
//            } catch (Exception e) {
//                setErrorResponse(HttpStatus.BAD_REQUEST, (HttpServletResponse) response, e);
//                e.printStackTrace();
//            }
            chain.doFilter(request,response);
        }
    }

    public void setErrorResponse(HttpStatus status, HttpServletResponse response, Throwable ex) {
        response.setStatus(status.value());
        response.setContentType("application/json");
        // A class used for errors
        ExceptionResponse apiError = ExceptionResponse
                .builder()
                .errorMessage("Unauthorized")
                .timeStamp(LocalDateTime.now())
                .debugMessage("No Header found")
                .responseStatus(HttpStatus.UNAUTHORIZED)
                .responseCode(401).build();
        try {
            String json = ObjectMapperUtil.convertToJson(apiError);
            System.out.println(json);
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

