package com.cement.app.inventory.controller;

import com.cement.app.inventory.dto.CreateCustomerRequest;
import com.cement.app.inventory.entity.Customer;
import com.cement.app.inventory.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Create Customer
    @PostMapping
    public Customer createCustomer(@RequestBody CreateCustomerRequest request) {
        return customerService.createCustomer(request);
    }

    // Get All Customers (Enterprise level)
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Search Customers by Name or Phone
    @GetMapping("/search")
    public List<Customer> searchCustomers(@RequestParam String term) {
        return customerService.searchCustomers(term);
    }

    // Delete Customer (Soft Delete)
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "Customer deleted successfully";
    }
}