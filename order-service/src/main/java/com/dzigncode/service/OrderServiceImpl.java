package com.dzigncode.service;

import com.dzigncode.mapper.OrderMapper;
import com.dzigncode.model.Order;
import com.dzigncode.publisher.OrderPublisher;
import com.dzigncode.repository.OrderRepository;
import com.dzigncode.shared.CreateOrderRequest;
import com.dzigncode.shared.GetOrderRequest;
import com.dzigncode.shared.OrderResponse;
import com.dzigncode.shared.OrderServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    OrderPublisher orderPublisher;

    @Override
    public void createOrder(CreateOrderRequest request, StreamObserver<OrderResponse> responseObserver) {

        Order order = orderMapper.convert(request);
        orderRepository.save(order);
        orderPublisher.publish(order);

        OrderResponse orderResponse = orderMapper.convert(order);

        responseObserver.onNext(orderResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getOrder(GetOrderRequest request, StreamObserver<OrderResponse> responseObserver) {

        Order orderById = orderRepository.getOrderById(request.getOrderId());

        OrderResponse orderResponse = orderMapper.convert(orderById);

        responseObserver.onNext(orderResponse);
        responseObserver.onCompleted();

    }
}
