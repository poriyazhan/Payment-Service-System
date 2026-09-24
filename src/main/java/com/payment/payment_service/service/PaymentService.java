package com.payment.payment_service.service;

import com.payment.payment_service.dto.payment.CreatePaymentRequest;
import com.payment.payment_service.dto.payment.PaymentResponse;
import com.payment.payment_service.enums.PaymentStatus;

public interface PaymentService {

    PaymentResponse createPayment(CreatePaymentRequest request);

    PaymentResponse updatePaymentStatus(
            String paymentReference,
            PaymentStatus newStatus
    );
}