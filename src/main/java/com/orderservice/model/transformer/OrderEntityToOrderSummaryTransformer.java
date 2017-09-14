package com.orderservice.model.transformer;

import com.orderservice.model.domain.OrderSummary;
import com.orderservice.model.entity.OrderEntity;
import com.orderservice.model.entity.OrderItemEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OrderEntityToOrderSummaryTransformer {


    public OrderSummary transform(OrderEntity entity) {

        if(null == entity)
            throw new IllegalArgumentException("OrderEntity is null");

        OrderSummary summary = new OrderSummary();
        summary.setOrderNumber(entity.getOrderNumber());

        return summary;

    }

    public int getItemCount(OrderEntity entity) {
        if (null == entity || null == entity.getOrderItemList()) {
            return 0;
        }

        List<OrderItemEntity> orderItemList = entity.getOrderItemList();
        int length = orderItemList.size();

        return IntStream.range(0, length).mapToObj(i -> orderItemList.get(i)).mapToInt(o -> o.getQuantity()).sum();
    }

    public BigDecimal getTotalAmount(OrderEntity entity) {
        if (null == entity || null == entity.getOrderItemList()) {
            return new BigDecimal(0);
        }

        BigDecimal totalAmt = new BigDecimal("0.00");
        int length = entity.getOrderItemList().size();
        return new BigDecimal(IntStream.range(0, length).mapToObj(i -> entity.getOrderItemList().get(i)).mapToDouble(o -> o.getSellingPrice().multiply(new BigDecimal(o.getQuantity())).doubleValue()).sum());

    }
}
