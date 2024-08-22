package com.juanlfr.eboutique.services;

import com.juanlfr.eboutique.dto.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    CheckoutService checkoutService;

    @KafkaListener(topics = "orders", groupId = "orders")
    public void receiveOrder(Purchase purchase) {
        System.out.println("Purchase consumed" + purchase.toString());
        checkoutService.placeOrder(purchase);
    }
}