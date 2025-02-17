package tobyspring.tobyspring.exrate;

import tobyspring.tobyspring.api.*;
import tobyspring.tobyspring.payment.ExRateProvider;

import java.math.BigDecimal;

public class WepApiExRateProvider implements ExRateProvider {

    private final ApiTemplate apiTemplate;

    public WepApiExRateProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExRate(String currency) {

        String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getForExRate(url);
    }

}
