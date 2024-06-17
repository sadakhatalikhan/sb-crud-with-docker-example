package com.sb.crud.docker.controller;

import com.sb.crud.docker.request.CustomerRequest;
import com.sb.crud.docker.response.APIResponse;
import com.sb.crud.docker.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse> createCustomer(@RequestBody CustomerRequest request) {
        return customerService.createCustomer(request);
    }

    @GetMapping("/getAll")
    public ResponseEntity<APIResponse> getAllCustomer() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/getById/{customerId}")
    public ResponseEntity<APIResponse> getByCustomerId(@PathVariable long customerId) {
        return customerService.getByCustomerId(customerId);
    }

    @DeleteMapping("/deleteById/{customerId}")
    public ResponseEntity<APIResponse> deleteByCustomerId(@PathVariable long customerId) {
        return customerService.deleteByCustomerId(customerId);
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<APIResponse> updateCustomer(@PathVariable long customerId, @RequestBody CustomerRequest request) {
        return customerService.updateCustomer(customerId, request);
    }
}
