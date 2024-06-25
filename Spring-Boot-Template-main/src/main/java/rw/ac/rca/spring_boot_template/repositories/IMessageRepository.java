package rw.ac.rca.spring_boot_template.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.spring_boot_template.models.Message;

public interface IMessageRepository extends JpaRepository<Message, Long> {
}
