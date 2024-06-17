package com.sb.crud.docker.service.impl;

import com.sb.crud.docker.model.CustomerModel;
import com.sb.crud.docker.request.CustomerRequest;
import com.sb.crud.docker.response.APIResponse;
import com.sb.crud.docker.response.CustomerResponse;
import com.sb.crud.docker.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static com.sb.crud.docker.mapper.CustomerMapper.modelToResponseMapper;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static List<CustomerModel> customers = new ArrayList<>();
    private static AtomicInteger c = new AtomicInteger(1);

    static {
        customers.add(new CustomerModel(c.getAndIncrement(), "testUser1", 21, "7234567811", "testuser1@gmail.com", "Bangalore", LocalDate.now()));
        customers.add(new CustomerModel(c.getAndIncrement(), "testUser2", 22, "7234567812", "testuser2@gmail.com", "Hyderabad", LocalDate.now()));
        customers.add(new CustomerModel(c.getAndIncrement(), "testUser3", 23, "7234567813", "testuser3@gmail.com", "Chennai", LocalDate.now()));
        customers.add(new CustomerModel(c.getAndIncrement(), "testUser4", 24, "7234567814", "testuser4@gmail.com", "Pune", LocalDate.now()));
    }

    @Override
    public ResponseEntity<APIResponse> createCustomer(CustomerRequest request) {

        CustomerModel customerModel = CustomerModel.builder()
                .customerId(c.getAndIncrement())
                .customerName(request.getCustomerName())
                .customerAge(request.getCustomerAge())
                .customerMobileNumber(request.getCustomerMobileNumber())
                .customerEmailAddress(request.getCustomerEmailAddress())
                .customerAddress(request.getCustomerAddress())
                .createdDate(LocalDate.now())
                .build();

        customers.add(customerModel);

        return ResponseEntity.ok(
                APIResponse.builder()
                        .errorCode(000)
                        .data(CustomerResponse.builder()
                                .customerId(customerModel.getCustomerId())
                                .customerName(customerModel.getCustomerName())
                                .customerAge(customerModel.getCustomerAge())
                                .customerMobileNumber(customerModel.getCustomerMobileNumber())
                                .customerEmailAddress(customerModel.getCustomerEmailAddress())
                                .customerAddress(customerModel.getCustomerAddress())
                                .createdDate(customerModel.getCreatedDate())
                                .build()
                        )
                        .build()
        );
    }

    @Override
    public ResponseEntity<APIResponse> getAllCustomers() {

        List<CustomerResponse> customerLists = customers.stream()
                .map(customerModel -> modelToResponseMapper(customerModel))
                .toList();

        return ResponseEntity.ok(
                APIResponse.builder()
                        .errorCode(000)
                        .data(customerLists)
                        .build()
        );
    }

    @Override
    public ResponseEntity<APIResponse> getByCustomerId(long customerId) {
        Optional<CustomerResponse> customerResponseOptional = customers.stream()
                .filter(customerModel -> customerModel.getCustomerId() == customerId)
                .map(customerModel -> modelToResponseMapper(customerModel))
                .findFirst();

        return customerResponseOptional.map(
                customerResponse -> ResponseEntity.ok(
                        APIResponse.builder()
                                .errorCode(0)
                                .data(customerResponse)
                                .build()
                )
        ).orElseGet(() -> ResponseEntity.ok(
                APIResponse.builder()
                        .errorCode(999)
                        .data("Customer is not available")
                        .build())
        );
    }

    @Override
    public ResponseEntity<APIResponse> deleteByCustomerId(long customerId) {

        boolean isRemoved = customers.removeIf(customerModel -> customerModel.getCustomerId() == customerId);

        if (isRemoved) {
            return ResponseEntity.ok(
                    APIResponse.builder()
                            .errorCode(0)
                            .data("Successfully Removed")
                            .build()
            );
        } else {
            return ResponseEntity.ok(
                    APIResponse.builder()
                            .errorCode(999)
                            .data("Customer is not available")
                            .build()
            );
        }
    }

    @Override
    public ResponseEntity<APIResponse> updateCustomer(long customerId, CustomerRequest request) {

        Optional<CustomerModel> customerModelOptional = customers.stream()
                .filter(customerModel -> customerModel.getCustomerId() == customerId)
                .map(customerModel -> {
                    customerModel.setCustomerName(request.getCustomerName());
                    customerModel.setCustomerAge(request.getCustomerAge());
                    customerModel.setCustomerMobileNumber(request.getCustomerMobileNumber());
                    customerModel.setCustomerEmailAddress(request.getCustomerEmailAddress());
                    customerModel.setCustomerAddress(request.getCustomerAddress());
                    return customerModel;
                })
                .findFirst();

        return customerModelOptional.map(customerResponse -> ResponseEntity.ok(
                APIResponse.builder()
                        .errorCode(000)
                        .data(CustomerResponse.builder()
                                .customerId(customerResponse.getCustomerId())
                                .customerName(customerResponse.getCustomerName())
                                .customerAge(customerResponse.getCustomerAge())
                                .customerMobileNumber(customerResponse.getCustomerMobileNumber())
                                .customerEmailAddress(customerResponse.getCustomerEmailAddress())
                                .customerAddress(customerResponse.getCustomerAddress())
                                .createdDate(customerResponse.getCreatedDate())
                                .build()
                        )
                        .build()
        )).orElseGet(() -> ResponseEntity.ok(
                APIResponse.builder()
                        .errorCode(999)
                        .data("Customer is not available")
                        .build()
        ));
    }
}
