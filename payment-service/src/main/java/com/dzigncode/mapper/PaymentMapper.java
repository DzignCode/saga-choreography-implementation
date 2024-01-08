package com.dzigncode.mapper;

import com.dzigncode.model.Order;
import com.dzigncode.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment convert(Order orderResponse) {
        return Payment.builder()
                .orderId(orderResponse.getOrderId())
                .customerId(orderResponse.getCustomerId())
                .sellerId(orderResponse.getSellerId())
                .paymentMethod(orderResponse.getPaymentMethod())
                .paymentStatus("SUCCESS")
                .paymentStatusNotes("Successfully paid by " + orderResponse.getPaymentMethod())
                .build();
    }
}
