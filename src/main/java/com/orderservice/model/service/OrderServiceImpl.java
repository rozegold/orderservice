package com.orderservice.model.service;

import com.orderservice.model.dao.OrderDao;
import com.orderservice.model.domain.OrderSummary;
import com.orderservice.model.entity.OrderEntity;
import com.orderservice.model.transformer.OrderEntityToOrderSummaryTransformer;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = null;
    private OrderEntityToOrderSummaryTransformer transformer =null;



    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }



    public void setTransformer(OrderEntityToOrderSummaryTransformer transformer) {
        this.transformer = transformer;
    }

    public List<OrderSummary> getOrderSummary(long customerId){


        List<OrderSummary> resultList = new LinkedList<>();

        List<OrderEntity> orderEntities = this.orderDao.findOrderByCustomer(customerId);

        orderEntities.stream().forEach(o -> {
            OrderSummary orderSummary = this.transformer.transform(o);
            resultList.add(orderSummary);
        });

        return  resultList;
    }
}
