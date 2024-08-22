package com.juanlfr.eboutique.services;

import com.juanlfr.eboutique.dto.Purchase;
import com.juanlfr.eboutique.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
