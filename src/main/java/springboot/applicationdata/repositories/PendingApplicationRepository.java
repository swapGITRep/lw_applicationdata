package springboot.applicationdata.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.applicationdata.entities.PendingApplication;


public interface PendingApplicationRepository extends JpaRepository<PendingApplication, UUID> {

	//@Query("select p from PENDING_APPLICATION  p where p.APPLICATION_NUMBER = ?1")
	PendingApplication findByApplicationNumber(String applicationNumber);
}
