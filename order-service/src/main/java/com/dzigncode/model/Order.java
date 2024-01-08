package com.dzigncode.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@DynamoDBTable(tableName = "order")
public class Order {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String orderId;
    @DynamoDBAttribute
    private Integer customerId;
    @DynamoDBAttribute
    private Integer sellerId;
    @DynamoDBAttribute
    private Product product;
    @DynamoDBAttribute
    private String paymentMethod;
    @DynamoDBAttribute
    private String deliveryLocation;
    @DynamoDBAttribute
    private String orderStatus;
    @DynamoDBAttribute
    private String createdTimestamp;
    @DynamoDBAttribute
    private String updatedTimestamp;
}
