package com.example.spring.aop.controller;

import com.example.spring.aop.annotation.LogExecutionTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @GetMapping("/payment")
    @LogExecutionTime("LogExcutionTime annotation")
    public String paymentDetails() throws InterruptedException {
        Thread.sleep(2000);
        return "I'm from payment details method";
    }
}
