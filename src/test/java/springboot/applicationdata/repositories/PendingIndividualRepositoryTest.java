package springboot.applicationdata.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import springboot.applicationdata.entities.PendingIndividual;
import springboot.applicationdata.entities.PendingApplication;

@SpringBootTest
class PendingIndividualRepositoryTest {

    @Autowired
    PendingIndividualRepository applicationIndividualRepository;

    @Autowired
    PendingApplicationRepository pendingApplicationRepository;

    PendingIndividual applicationIndividual;
    
    PendingApplication pendingApplication;

    @BeforeEach
    void setUp() {
    	pendingApplication = pendingApplicationRepository.findAll().get(0);
    }

    @Transactional
    @Test
    void testAddApplicant() {
    	PendingIndividual indv1 = PendingIndividual.builder()
                .id(UUID.randomUUID())
                .firstName("Firstname 1")
                .lastName("Lastname 1")
                .pendingApplication(pendingApplication)
                .version(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

    	PendingIndividual savedApplicant = applicationIndividualRepository.save(indv1);

        System.out.println(savedApplicant.getFirstName());
        
        assertThat(savedApplicant).isNotNull();
  
    }
}











