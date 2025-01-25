package tobyspring.tobyspring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.tobyspring.payment.Payment;
import tobyspring.tobyspring.payment.PaymentService;

import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment = paymentService.prepare(100L, "USD", java.math.BigDecimal.valueOf(50.7));
        System.out.println("Payment: "+payment);
    }

}
