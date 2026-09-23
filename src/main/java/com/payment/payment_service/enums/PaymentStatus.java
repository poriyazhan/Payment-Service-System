package com.payment.payment_service.enums;

public enum PaymentStatus {

    CREATED,
    PROCESSING,
    SUCCESS,
    FAILED,
    CANCELLED,
    REFUND_INITIATED,
    PARTIALLY_REFUNDED,
    REFUNDED
}