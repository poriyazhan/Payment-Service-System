package com.payment.payment_service.service;

import com.payment.payment_service.dto.payment.CreatePaymentRequest;
import com.payment.payment_service.dto.payment.PaymentResponse;

public interface PaymentService {

    PaymentResponse createPayment(CreatePaymentRequest request);
}