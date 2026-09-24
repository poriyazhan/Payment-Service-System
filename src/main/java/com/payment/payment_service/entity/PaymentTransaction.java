package com.payment.payment_service.entity;

import com.payment.payment_service.enums.PaymentStatus;
import com.payment.payment_service.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payment_transactions")
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "transaction_reference",
            nullable = false,
            unique = true
    )
    private String transactionReference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "payment_id",
            nullable = false
    )
    private Payment payment;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "transaction_type",
            nullable = false
    )
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false
    )
    private PaymentStatus status;

    @Column(name = "provider_transaction_id")
    private String providerTransactionId;

    @Column(name = "failure_code")
    private String failureCode;

    @Column(name = "failure_message")
    private String failureMessage;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

}