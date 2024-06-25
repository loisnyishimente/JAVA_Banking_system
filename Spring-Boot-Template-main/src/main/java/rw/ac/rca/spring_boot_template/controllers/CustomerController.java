package rw.ac.rca.spring_boot_template.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.spring_boot_template.models.Customer;
import rw.ac.rca.spring_boot_template.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(createdCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.findCustomerById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        Customer customer = customerService.findCustomerByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Customer updatedCustomer = customerService.updateCustomer(id, customerDetails);
        return ResponseEntity.ok(updatedCustomer);
    }

//    @GetMapping("/")
//    public ResponseEntity<List<Customer>> getAllCustomers() {
//        List<Customer> customers = customerService.findAll();
//        return ResponseEntity.ok(customers);
//    }
}
