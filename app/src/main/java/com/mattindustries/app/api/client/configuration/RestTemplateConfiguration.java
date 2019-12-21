package com.mattindustries.app.api.client.configuration;

import com.mattindustries.app.api.client.users.handlers.RestTemplateErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    RestTemplate getUserByPrincipalRestTemplate() {

        Logger logger = LoggerFactory.getLogger(getClass());

        ClientHttpRequestInterceptor interceptor = (HttpRequest request, byte[] body, ClientHttpRequestExecution
                execution) -> {
            logger.info("request to URI {} with verb '{}'",
                    request.getURI(),
                    request.getMethod());
            return execution.execute(request, body);
        };

        return new RestTemplateBuilder().
                additionalInterceptors(interceptor)
                .errorHandler(new RestTemplateErrorHandler())
                .build();
    }
}
