package com.payment.payment_service.service.impl;

import com.payment.payment_service.service.PaymentService;

import com.payment.payment_service.dto.payment.CreatePaymentRequest;
import com.payment.payment_service.dto.payment.PaymentResponse;
import com.payment.payment_service.entity.Payment;
import com.payment.payment_service.enums.PaymentStatus;
import com.payment.payment_service.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public PaymentResponse createPayment(CreatePaymentRequest request) {

        Payment payment = new Payment();

        payment.setPaymentReference(generatePaymentReference());
        payment.setOrderId(request.getOrderId());
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency().toUpperCase());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setStatus(PaymentStatus.CREATED);
        payment.setDescription(request.getDescription());

        LocalDateTime now = LocalDateTime.now();

        payment.setCreatedAt(now);
        payment.setUpdatedAt(now);

        Payment savedPayment = paymentRepository.save(payment);

        return mapToResponse(savedPayment);
    }

    private String generatePaymentReference() {

        return "PAY-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();
    }

    private PaymentResponse mapToResponse(Payment payment) {

        PaymentResponse response = new PaymentResponse();

        response.setPaymentId(payment.getId());
        response.setPaymentReference(payment.getPaymentReference());
        response.setOrderId(payment.getOrderId());
        response.setAmount(payment.getAmount());
        response.setCurrency(payment.getCurrency());
        response.setPaymentMethod(payment.getPaymentMethod());
        response.setStatus(payment.getStatus());
        response.setDescription(payment.getDescription());

        return response;
    }
}