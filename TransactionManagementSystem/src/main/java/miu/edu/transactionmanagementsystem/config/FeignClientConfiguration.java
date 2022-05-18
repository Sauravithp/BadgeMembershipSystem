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

//    class FeignClientConfiguration(private val authorizedClientManager: OAuth2AuthorizedClientManager) {
//
//        @Bean
//        fun requestInterceptor(): RequestInterceptor = RequestInterceptor { template ->
//            if (template.headers()["Authorization"].isNullOrEmpty()) {
//                val accessToken = getAccessToken()
//                logger.debug { "ACCESS TOKEN TYPE: ${accessToken?.tokenType?.value}" }
//                logger.debug { "ACCESS TOKEN: ${accessToken?.tokenValue}" }
//                template.header("Authorization", "Bearer ${accessToken?.tokenValue}")
//            }
//        }
//
//        private fun getAccessToken(): OAuth2AccessToken? {
//            val request = OAuth2AuthorizeRequest
//                    .withClientRegistrationId("keycloak") // <- Here you load your registered client
//                    .principal(SecurityContextHolder.getContext().authentication)
//                    .build()
//            return authorizedClientManager.authorize(request)?.accessToken
//        }
//
//    }
}
