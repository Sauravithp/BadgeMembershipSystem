package miu.edu.transactionmanagementsystem.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {


    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("sauravi", "sauravi");
    }


}
