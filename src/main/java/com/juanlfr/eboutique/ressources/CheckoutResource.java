package com.juanlfr.eboutique.ressources;

import com.juanlfr.eboutique.dto.Purchase;
import com.juanlfr.eboutique.dto.PurchaseResponse;
import com.juanlfr.eboutique.services.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin("http://localhost:4200")
public class CheckoutResource {

    @Autowired
    CheckoutService checkoutService;

    @PostMapping("/purchase")
    public PurchaseResponse checkout(@RequestBody Purchase purchase) {
        System.out.println(purchase.toString());
        return checkoutService.placeOrder(purchase);
    }

}
