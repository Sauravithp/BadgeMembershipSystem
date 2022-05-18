package miu.edu.transactionmanagementsystem.filter;

import com.nimbusds.jwt.SignedJWT;
import lombok.SneakyThrows;
import miu.edu.transactionmanagementsystem.exception.ExceptionResponse;
import miu.edu.transactionmanagementsystem.util.ObjectMapperUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
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
            String header = httpRequest.getHeader("Authorization");
            try {
                //header
                String token=header.substring(6);
                String headerJson = parseJWTHeader(token);
                //decrypt the jwt
                if(headerJson.contains("JWT")){
                    chain.doFilter(request, response);
                } else{
                    setErrorResponse(HttpStatus.BAD_REQUEST, (HttpServletResponse) response);
                }
            } catch (Exception e) {
                setErrorResponse(HttpStatus.BAD_REQUEST, (HttpServletResponse) response);
                e.printStackTrace();
            }
        }
    }

    @SneakyThrows
    private String parseJWTHeader(String accessToken) {
        String header;
        try {
            var decodedJWT = SignedJWT.parse(accessToken);
            header = decodedJWT.getHeader().toString();
        } catch (ParseException e) {
            throw new Exception("Invalid token!");
        }
        return header;
    }

    public void setErrorResponse(HttpStatus status, HttpServletResponse response) {
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

