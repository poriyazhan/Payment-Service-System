package com.payment.payment_service.service.impl;

import com.payment.payment_service.entity.PaymentTransaction;
import com.payment.payment_service.enums.TransactionType;
import com.payment.payment_service.exception.InvalidPaymentStateException;
import com.payment.payment_service.repository.PaymentTransactionRepository;
import com.payment.payment_service.service.PaymentService;

import com.payment.payment_service.dto.payment.CreatePaymentRequest;
import com.payment.payment_service.dto.payment.PaymentResponse;
import com.payment.payment_service.entity.Payment;
import com.payment.payment_service.enums.PaymentStatus;
import com.payment.payment_service.repository.PaymentRepository;
import com.payment.payment_service.service.PaymentStateMachine;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentTransactionRepository paymentTransactionRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentTransactionRepository paymentTransactionRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentTransactionRepository = paymentTransactionRepository;
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

    @Override
    @Transactional
    public PaymentResponse updatePaymentStatus(String paymentReference,PaymentStatus newStatus) {



            Payment payment = paymentRepository
                    .findByPaymentReference(paymentReference)
                    .orElseThrow(() ->
                            new EntityNotFoundException(
                                    "Payment not found: "
                                            + paymentReference
                            ));

            PaymentStatus currentStatus = payment.getStatus();

            if (!PaymentStateMachine.isValidTransition(
                    currentStatus,
                    newStatus)) {

                throw new InvalidPaymentStateException(
                        "Invalid payment status transition: "
                                + currentStatus
                                + " -> "
                                + newStatus
                );
            }

            payment.setStatus(newStatus);
            payment.setUpdatedAt(LocalDateTime.now());

            paymentRepository.save(payment);

            PaymentTransaction transaction =
                    new PaymentTransaction();

            transaction.setTransactionReference(
                    generateTransactionReference()
            );

            transaction.setPayment(payment);

            transaction.setTransactionType(
                    TransactionType.PAYMENT
            );

            transaction.setStatus(newStatus);

            transaction.setCreatedAt(LocalDateTime.now());

            paymentTransactionRepository.save(transaction);

            return mapToResponse(payment);

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

    private String generateTransactionReference() {

        return "TXN-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();
    }
}