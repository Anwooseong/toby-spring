package tobyspring.tobyspring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment1 = paymentService.prepare(100L, "USD", java.math.BigDecimal.valueOf(50.7));
        System.out.println("Payment1: "+payment1);
        System.out.println("--------------------------\n");

        Thread.sleep(1000);
        Payment payment2 = paymentService.prepare(100L, "USD", java.math.BigDecimal.valueOf(50.7));
        System.out.println("Payment2: "+payment2);
        System.out.println("--------------------------\n");

        Thread.sleep(2000);

        Payment payment3 = paymentService.prepare(100L, "USD", java.math.BigDecimal.valueOf(50.7));
        System.out.println("Payment2: "+payment3);
        System.out.println("--------------------------\n");
    }

}
