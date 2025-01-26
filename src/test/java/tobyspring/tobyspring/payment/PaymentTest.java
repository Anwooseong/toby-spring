package tobyspring.tobyspring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.*;

public class PaymentTest {
    @Test
    void createPrepared() {
        // given
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        // when
        Payment payment = Payment.createPrepared(
                1L, "USD", BigDecimal.TEN, BigDecimal.valueOf(1_000), LocalDateTime.now(clock)
        );

        // then
        Assertions.assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000));
        Assertions.assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30));
    }

    @Test
    void isValid() {
        // given
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        // when
        Payment payment = Payment.createPrepared(
                1L, "USD", BigDecimal.TEN, BigDecimal.valueOf(1_000), LocalDateTime.now(clock)
        );

        // then
        Assertions.assertThat(payment.isValid(clock)).isTrue();
    }
}
