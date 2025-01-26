package tobyspring.tobyspring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.tobyspring.TestPaymentConfig;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
class PaymentServiceSpringTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    ExRateProviderStub exRateProviderStub;

    @Autowired
    Clock clock;

    @Test
    void convertedAmount() {
        // given
        // when
        // exRate: 1000
        Payment payment1 = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // exRate: 500
        exRateProviderStub.setExRate(BigDecimal.valueOf(500));
        System.out.println(exRateProviderStub.getExRate());
        Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(payment1.getExRate()).isEqualByComparingTo(BigDecimal.valueOf(1_000));
        assertThat(payment1.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000));
        assertThat(payment2.getExRate()).isEqualByComparingTo(BigDecimal.valueOf(500));
        assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(5_000));

    }

    @Test
    void validUntil() {
        // given
        // when
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);

        // then
        Assertions.assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }
}