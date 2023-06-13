package springboot.applicationdata.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;

import springboot.applicationdata.model.PendingIndividualDTO;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface PendingIndividualService {

    Page<PendingIndividualDTO> listIndividuals(String lastName, String pendingApplicationId, Integer pageNumber, Integer pageSize);

    Optional<PendingIndividualDTO> getIndividualById(UUID id);

    PendingIndividualDTO saveNewIndividual(PendingIndividualDTO individualDTO);

}
