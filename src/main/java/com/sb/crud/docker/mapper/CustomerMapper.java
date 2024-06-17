package com.sb.crud.docker.mapper;

import com.sb.crud.docker.model.CustomerModel;
import com.sb.crud.docker.response.CustomerResponse;

public class CustomerMapper {
    public static CustomerResponse modelToResponseMapper(CustomerModel customerModel) {
        return CustomerResponse.builder()
                .customerId(customerModel.getCustomerId())
                .customerName(customerModel.getCustomerName())
                .customerAge(customerModel.getCustomerAge())
                .customerMobileNumber(customerModel.getCustomerMobileNumber())
                .customerEmailAddress(customerModel.getCustomerEmailAddress())
                .customerAddress(customerModel.getCustomerAddress())
                .createdDate(customerModel.getCreatedDate())
                .build();
    }
}
