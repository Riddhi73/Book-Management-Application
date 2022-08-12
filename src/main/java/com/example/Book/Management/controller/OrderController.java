package com.example.Book.Management.controller;

import com.example.Book.Management.dto.checkout.CheckOutItemDto;
import com.example.Book.Management.dto.checkout.StripeResponse;
import com.example.Book.Management.service.AuthService;
import com.example.Book.Management.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private AuthService authService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkOutList(@RequestBody List<CheckOutItemDto> checkOutItemDtoList) throws StripeException {
        Session session = orderService.createSession(checkOutItemDtoList);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);

    }
}
