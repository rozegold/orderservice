package com.orderservice.model.transformer;

import com.orderservice.model.domain.OrderSummary;
import com.orderservice.model.entity.OrderEntity;
import com.orderservice.model.entity.OrderItemEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderEntityToOrderSummaryTransformerTest {


    private OrderEntityToOrderSummaryTransformer subject = null;

    private OrderEntity entityFixture;
    String ordrNumberFixture = UUID.randomUUID().toString();

    @Before
    public void setup() {

        subject = new OrderEntityToOrderSummaryTransformer();

        entityFixture  = new OrderEntity();

        OrderItemEntity itemEntityFixture1 = new OrderItemEntity();
        itemEntityFixture1.setQuantity(1);
        itemEntityFixture1.setSellingPrice(new BigDecimal("10"));
        OrderItemEntity itemEntityFixture2 = new OrderItemEntity();
        itemEntityFixture2.setQuantity(2);
        itemEntityFixture2.setSellingPrice(new BigDecimal("1.50"));

        entityFixture.getOrderItemList().add(itemEntityFixture1);
        entityFixture.getOrderItemList().add(itemEntityFixture2);


        entityFixture.setOrderNumber(ordrNumberFixture);
    }

    @Test
    public void test_transform_success(){


        OrderSummary result = subject.transform(entityFixture);
        assertNotNull(result);
        assertEquals(ordrNumberFixture, result.getOrderNumber());
    }

    @Test
    public void testItemCountForOrderEntity(){
        assertEquals(3, subject.getItemCount(entityFixture));
    }

    @Test
    public void testTotalAmount(){
        assertEquals(new BigDecimal(13), subject.getTotalAmount(entityFixture));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTransformIfInputIsNull(){
        subject.transform(null);

    }

    @Test
    public void testTransformWhenNoItemsInOrder(){
        entityFixture.setOrderItemList(new LinkedList<>());
        OrderSummary result = subject.transform(entityFixture);

        assertNotNull(result);
        assertEquals(0, subject.getItemCount(entityFixture));
        assertEquals(new BigDecimal("0"), subject.getTotalAmount(entityFixture));

    }

}