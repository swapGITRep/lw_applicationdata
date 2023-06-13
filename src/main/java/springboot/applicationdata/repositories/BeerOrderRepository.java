package springboot.applicationdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.applicationdata.entities.BeerOrder;

import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID> {
}
