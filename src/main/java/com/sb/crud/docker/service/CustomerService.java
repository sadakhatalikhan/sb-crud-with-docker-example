package com.sb.crud.docker.service;

import com.sb.crud.docker.request.CustomerRequest;
import com.sb.crud.docker.response.APIResponse;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<APIResponse> createCustomer(CustomerRequest request);
    ResponseEntity<APIResponse> getAllCustomers();
    ResponseEntity<APIResponse> getByCustomerId(long customerId);
    ResponseEntity<APIResponse> deleteByCustomerId(long customerId);
    ResponseEntity<APIResponse> updateCustomer(long customerId, CustomerRequest request);
}
