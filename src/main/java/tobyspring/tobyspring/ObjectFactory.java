package tobyspring.tobyspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.tobyspring.exrate.CachedExRateProvider;
import tobyspring.tobyspring.payment.ExRateProvider;
import tobyspring.tobyspring.payment.PaymentService;
import tobyspring.tobyspring.exrate.WepApiExRateProvider;

@Configuration
public class ObjectFactory {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider());
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WepApiExRateProvider();
    }
}
