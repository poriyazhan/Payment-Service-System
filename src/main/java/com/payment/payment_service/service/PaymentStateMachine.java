package com.payment.payment_service.service;

import com.payment.payment_service.enums.PaymentStatus;

import java.util.Map;
import java.util.Set;

public class PaymentStateMachine {

    private static final Map<PaymentStatus, Set<PaymentStatus>> ALLOWED_TRANSITIONS =
            Map.of(
                    PaymentStatus.CREATED,
                    Set.of(PaymentStatus.PROCESSING),

                    PaymentStatus.PROCESSING,
                    Set.of(
                            PaymentStatus.SUCCESS,
                            PaymentStatus.FAILED
                    ),

                    PaymentStatus.SUCCESS,
                    Set.of(),

                    PaymentStatus.FAILED,
                    Set.of(),

                    PaymentStatus.CANCELLED,
                    Set.of(),

                    PaymentStatus.REFUND_INITIATED,
                    Set.of(
                            PaymentStatus.REFUNDED,
                            PaymentStatus.PARTIALLY_REFUNDED
                    ),

                    PaymentStatus.PARTIALLY_REFUNDED,
                    Set.of(PaymentStatus.REFUNDED),

                    PaymentStatus.REFUNDED,
                    Set.of()
            );

    public static boolean isValidTransition(
            PaymentStatus currentStatus,
            PaymentStatus newStatus) {

        return ALLOWED_TRANSITIONS
                .getOrDefault(currentStatus, Set.of())
                .contains(newStatus);
    }
}