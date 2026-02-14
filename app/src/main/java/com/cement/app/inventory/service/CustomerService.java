package com.cement.app.inventory.service;

import com.cement.app.common.util.TenantContext;
import com.cement.app.inventory.dto.CreateCustomerRequest;
import com.cement.app.inventory.entity.Customer;
import com.cement.app.inventory.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private Long getCurrentTenant() {
        Long tenantId = TenantContext.getTenant();
        return (tenantId != null) ? tenantId : 1L;
    }

    public Customer createCustomer(CreateCustomerRequest request) {

        Customer customer = new Customer();
        customer.setCustomerName(request.getCustomerName());
        customer.setPhone(request.getPhone());
        customer.setCreditLimit(request.getCreditLimit());

        customer.setEnterpriseId(getCurrentTenant());

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findByEnterpriseId(getCurrentTenant());
    }
}

