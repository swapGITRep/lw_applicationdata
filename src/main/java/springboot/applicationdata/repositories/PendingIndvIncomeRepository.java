package springboot.applicationdata.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.applicationdata.entities.PendingIndvIncome;


public interface PendingIndvIncomeRepository extends JpaRepository<PendingIndvIncome, UUID> {
	
	Page<PendingIndvIncome> findAllByApplicationIndividualLastNameIsLikeIgnoreCase(String lastName, Pageable pageable);
}
