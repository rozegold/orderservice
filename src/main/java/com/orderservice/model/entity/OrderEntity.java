package com.orderservice.model.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class OrderEntity {


    private long id;
    private String orderNumber;
    private String orderLable;
    private long customerId;
    private long shippingAddressId;
    private long billingAddressId;
    private Date completionDate;
    private List<OrderItemEntity>  orderItemList = new LinkedList<OrderItemEntity>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderLable() {
        return orderLable;
    }

    public void setOrderLable(String orderLable) {
        this.orderLable = orderLable;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public long getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(long billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public List<OrderItemEntity> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemEntity> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
