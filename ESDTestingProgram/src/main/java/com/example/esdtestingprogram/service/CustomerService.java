package com.example.esdtestingprogram.service;

import com.example.esdtestingprogram.dto.CustomerRequest;
import com.example.esdtestingprogram.dto.CustomerResponse;
import com.example.esdtestingprogram.entity.Customer;
import com.example.esdtestingprogram.exception.CustomerNotFoundException;
import com.example.esdtestingprogram.helper.EncryptionService;
import com.example.esdtestingprogram.helper.JWTHelper;
import com.example.esdtestingprogram.mapper.CustomerMapper;
import com.example.esdtestingprogram.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public String createCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toCustomer(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        customerRepo.save(customer);
        return "Customer Created Successfully";
    }

    public Customer getCustomer(String email) {
        return customerRepo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
                ));
    }

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = getCustomer(email);
        return customerMapper.toCustomerResponse(customer);
    }

}
