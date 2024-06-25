package rw.ac.rca.spring_boot_template.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.spring_boot_template.models.Customer;

import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByAccount(String account);
}
