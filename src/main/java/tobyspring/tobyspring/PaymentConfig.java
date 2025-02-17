package tobyspring.tobyspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import tobyspring.tobyspring.api.ApiTemplate;
import tobyspring.tobyspring.api.ErApiExExtractor;
import tobyspring.tobyspring.api.SimpleApiExecutor;
import tobyspring.tobyspring.exrate.CachedExRateProvider;
import tobyspring.tobyspring.exrate.RestTemplateExRateProvider;
import tobyspring.tobyspring.payment.ExRateProvider;
import tobyspring.tobyspring.payment.PaymentService;
import tobyspring.tobyspring.exrate.WepApiExRateProvider;

import java.time.Clock;

@Configuration
public class PaymentConfig {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public ApiTemplate apiTemplate() {
        return new ApiTemplate(new SimpleApiExecutor(), new ErApiExExtractor());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new RestTemplateExRateProvider(restTemplate());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
