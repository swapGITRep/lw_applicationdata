package springboot.applicationdata.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import springboot.applicationdata.model.PendingIndvIncomeDTO;

public interface PendingIndvIncomeService {

    Page<PendingIndvIncomeDTO> listIncomeByIndividual(String lastName, Integer pageNumber, Integer pageSize);

    Optional<PendingIndvIncomeDTO> getIncomeById(UUID id);

    PendingIndvIncomeDTO saveNewIncome(PendingIndvIncomeDTO incomeDTO);

}
