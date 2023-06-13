package springboot.applicationdata.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import springboot.applicationdata.entities.PendingIndvIncome;
import springboot.applicationdata.entities.PendingIndividual;
import springboot.applicationdata.entities.PendingApplication;
import springboot.applicationdata.model.Frequency;

@SpringBootTest
class PendingIndvIncomeRepositoryTest {

    @Autowired
    PendingIndividualRepository applicationIndividualRepository;

    @Autowired
    PendingApplicationRepository pendingApplicationRepository;
    
    @Autowired
    PendingIndvIncomeRepository applicationIncomeRepository;

    PendingIndividual applicationIndividual;
    PendingIndvIncome applicationIncome;
    
    PendingApplication pendingApplication;

    @BeforeEach
    void setUp() {
    	pendingApplication = pendingApplicationRepository.findAll().get(0);
    }

    @Transactional
    @Test
    void testAddApplicantIncome() {
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
        assertThat(savedApplicant).isNotNull();

        
        
        PendingIndvIncome income1 = PendingIndvIncome.builder()
        		.id(UUID.randomUUID())
        		.amount(new BigDecimal(350.99))
        		.frequency(Frequency.MONTHLY)
        		.applicationIndividual(indv1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
        		.build();
        
        PendingIndvIncome savedIncome = applicationIncomeRepository.save(income1);
        assertThat(savedIncome).isNotNull();
        
        
        
    }
}











