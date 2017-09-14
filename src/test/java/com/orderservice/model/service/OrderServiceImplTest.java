package com.orderservice.model.service;

import com.orderservice.model.dao.OrderDao;
import com.orderservice.model.domain.OrderSummary;
import com.orderservice.model.entity.OrderEntity;
import com.orderservice.model.transformer.OrderEntityToOrderSummaryTransformer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {

    private OrderServiceImpl subject;
    private long customerId = 1L;
    private OrderEntity orderEntityFixture;
    private OrderSummary orderSummaryFixture;

    @Mock
    private OrderEntityToOrderSummaryTransformer transformerMock;



    private List<OrderEntity> orderEntityListFixture;
    @Mock
    private OrderDao orderDaoMock;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        subject = new OrderServiceImpl();
        orderEntityFixture = new OrderEntity();
        orderSummaryFixture = new OrderSummary();
        orderEntityListFixture = new LinkedList<>();
        orderEntityListFixture.add(orderEntityFixture);
        subject.setTransformer(transformerMock);
        subject.setOrderDao(orderDaoMock);

    }

    @Test
    public void testOrderSummarySuccess(){


        when(orderDaoMock.findOrderByCustomer(customerId)).thenReturn(orderEntityListFixture);
        when(transformerMock.transform(orderEntityFixture)).thenReturn(orderSummaryFixture);

        List<OrderSummary> resultList = subject.getOrderSummary(customerId);

        assertNotNull(resultList);
        assertEquals(1, resultList.size());
        assertEquals(orderSummaryFixture, resultList.get(0));

    }

}