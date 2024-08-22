package com.juanlfr.eboutique.services;

import com.juanlfr.eboutique.dao.CustomerRepository;
import com.juanlfr.eboutique.dto.Purchase;
import com.juanlfr.eboutique.dto.PurchaseResponse;
import com.juanlfr.eboutique.entities.Customer;
import com.juanlfr.eboutique.entities.Order;
import com.juanlfr.eboutique.entities.OrderItem;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {
        Order order = purchase.getOrder();
        //Tracking number
        String trackingNumber = generateTrakingNumber();
        order.setOrderTrackingNumber(trackingNumber);
        //Populate order items in the Order
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(order::add);
        //Populate address items in the Order
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());
        //Populate curstomer with the Order
        Customer customer = purchase.getCustomer();
        customer.add(order);
        //Save to db
        customerRepository.save(customer);
        return new PurchaseResponse(trackingNumber);
    }

    private String generateTrakingNumber() {
        return UUID.randomUUID().toString();
    }
}
