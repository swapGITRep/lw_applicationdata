package springboot.applicationdata.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import springboot.applicationdata.entities.PendingIndividual;


public interface PendingIndividualRepository extends JpaRepository<PendingIndividual, UUID> {
	
	 Page<PendingIndividual> findAllByLastNameIsLikeIgnoreCase(String lastName, Pageable pageable);

	 Page<PendingIndividual> findByPendingApplicationApplicationNumber(String applicationNumber, Pageable pageable);
	 

}
