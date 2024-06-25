package rw.ac.rca.spring_boot_template.services.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.ac.rca.spring_boot_template.models.Customer;
import rw.ac.rca.spring_boot_template.repositories.ICustomerRepository;
import rw.ac.rca.spring_boot_template.services.CustomerService;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setFirstname(customerDetails.getFirstname());
        customer.setLastname(customerDetails.getLastname());
        customer.setEmail(customerDetails.getEmail());
        customer.setMobile(customerDetails.getMobile());
        customer.setDob(customerDetails.getDob());
        customer.setAccount(customerDetails.getAccount());
        customer.setBalance(customerDetails.getBalance());
        return customerRepository.save(customer);
    }
}
