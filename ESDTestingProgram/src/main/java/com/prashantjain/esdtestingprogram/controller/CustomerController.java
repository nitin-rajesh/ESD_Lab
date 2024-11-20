package com.prashantjain.esdtestingprogram.controller;

import com.prashantjain.esdtestingprogram.dto.CustomerRequest;
import com.prashantjain.esdtestingprogram.dto.CustomerResponse;
import com.prashantjain.esdtestingprogram.dto.LoginRequest;
import com.prashantjain.esdtestingprogram.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("email") String email) {
        return ResponseEntity.ok(customerService.retrieveCustomer(email));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody CustomerRequest customerRequest) {
        try {
            customerService.createCustomer(customerRequest);
            return ResponseEntity.ok("User created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
