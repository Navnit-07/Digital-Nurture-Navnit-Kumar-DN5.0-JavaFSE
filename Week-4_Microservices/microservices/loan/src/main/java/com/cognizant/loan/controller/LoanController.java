package com.cognizant.loan.controller;

import com.cognizant.loan.model.Loan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    // Dummy response - no backend connectivity
    @GetMapping("/loans/{number}")
    public Loan getLoan(@PathVariable String number) {
        return new Loan("H00987987972342", "car", 400000, 3258, 18);
    }
}
