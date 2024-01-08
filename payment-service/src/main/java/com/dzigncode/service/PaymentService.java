package com.dzigncode.service;

import com.dzigncode.mapper.PaymentMapper;
import com.dzigncode.model.Order;
import com.dzigncode.model.OrderState;
import com.dzigncode.model.OrderStatus;
import com.dzigncode.model.Payment;
import com.dzigncode.publisher.PaymentPublisher;
import com.dzigncode.repository.PaymentRespository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentMapper paymentMapper;
    private PaymentRespository paymentRepository;
    private PaymentPublisher paymentPublisher;

    public PaymentService(PaymentMapper paymentMapper, PaymentRespository paymentRepository, PaymentPublisher paymentPublisher) {
        this.paymentMapper = paymentMapper;
        this.paymentRepository = paymentRepository;
        this.paymentPublisher = paymentPublisher;
    }

    public void send(Order orderResponse) {
        Payment payment = paymentMapper.convert(orderResponse);
        paymentRepository.save(payment);
        paymentPublisher.publish(payment);
        OrderStatus orderStatus = OrderStatus.builder()
                .orderState(OrderState.ORDER_PAID)
                .orderId(orderResponse.getOrderId())
                .message("Successfully paid by " + payment.getPaymentMethod())
                .build();
        paymentPublisher.publish(orderStatus);

    }
}
