package springboot.applicationdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.applicationdata.entities.Customer;

import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
