//package com.payment.payment_service.service;
//
//
//import com.payment.payment_service.entity.Payment;
//import com.payment.payment_service.repository.PaymentRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//@Configuration
//public class PaymentPersistenceTestRunner {
//
//    @Bean
//    CommandLineRunner testPaymentPersistence(PaymentRepository paymentRepository) {
//
//        return args -> {
//
//            Payment payment = new Payment();
//
//            payment.setPaymentReference("PAY-TEST-001");
//            payment.setOrderId("ORD-TEST-001");
//            payment.setAmount(new BigDecimal("2500.00"));
//            payment.setCurrency("INR");
//            payment.setPaymentMethod("UPI");
//            payment.setStatus("CREATED");
//            payment.setDescription("Test payment");
//            payment.setCreatedAt(LocalDateTime.now());
//            payment.setUpdatedAt(LocalDateTime.now());
//
//            Payment savedPayment = paymentRepository.save(payment);
//
//            System.out.println(
//                    "Saved Payment ID: " + savedPayment.getId()
//            );
//        };
//    }
//}