package com.dzigncode.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.dzigncode.model.Payment;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRespository {

    private DynamoDBMapper dynamoDBMapper;

    public PaymentRespository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Payment save(Payment payment) {
        dynamoDBMapper.save(payment);
        return payment;
    }
}
