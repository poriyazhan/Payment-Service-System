package com.payment.payment_service.dto.payment;

import com.payment.payment_service.enums.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class CreatePaymentRequest {

    @NotBlank(message = "Order ID is required")
    @Size(max = 50, message = "Order ID must not exceed 50 characters")
    private String orderId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "Currency is required")
    @Size(min = 3, max = 3, message = "Currency must contain exactly 3 characters")
    private String currency;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

}