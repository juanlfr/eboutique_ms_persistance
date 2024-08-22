package com.juanlfr.eboutique.dto;


import com.juanlfr.eboutique.entities.Address;
import com.juanlfr.eboutique.entities.Customer;
import com.juanlfr.eboutique.entities.Order;
import com.juanlfr.eboutique.entities.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
