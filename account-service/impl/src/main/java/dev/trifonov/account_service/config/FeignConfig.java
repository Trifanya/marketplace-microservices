package dev.trifonov.account_service.config;

import feign.Logger;
import lombok.RequiredArgsConstructor;
import me.bvn13.openfeign.logger.normalized.NormalizedFeignLogger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.openfeign.security.OAuth2AccessTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

/**
 * @ConditionalOnBean - бин будет создан, только если в контексте приложения есть бины указанных типов.
 * @ConditionalOnMissingBean - бин будет создан, если в контексте приложения нет бинов указанных типов.
 * @ConditionalOnProperty - бин будет создан, только если указанное свойство присутствует и имеет значение.
 */

//@Configuration
@RequiredArgsConstructor
public class FeignConfig {

    //private final OAuth2ClientProperties oAuth2ClientProperties;

    @Bean
    @ConditionalOnBean({OAuth2AuthorizedClientService.class, ClientRegistrationRepository.class})
    public OAuth2AuthorizedClientManager feignOAuth2AuthorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        return new AuthorizedClientServiceOAuth2AuthorizedClientManager(clientRegistrationRepository,
                oAuth2AuthorizedClientService);
    }

    @Bean
    @ConditionalOnBean(OAuth2AuthorizedClientManager.class)
    public OAuth2AccessTokenInterceptor defaultOAuth2AccessTokenInterceptor(
            OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager) {
        return new OAuth2AccessTokenInterceptor("keycloak", oAuth2AuthorizedClientManager);
    }

/*    @Bean
    public RequestInterceptor requestInterceptor(@Value("${feign-client.catalog-service.username}") String username,
                                                 @Value("${feign-client.catalog-service.password}") String password) {
        return new BasicAuthRequestInterceptor(username, password);
    }*/

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Logger logger() {
        return new NormalizedFeignLogger();
    }

}
