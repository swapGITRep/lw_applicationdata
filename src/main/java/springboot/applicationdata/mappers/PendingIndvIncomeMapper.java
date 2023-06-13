package springboot.applicationdata.mappers;

import org.mapstruct.Mapper;


import springboot.applicationdata.entities.PendingIndvIncome;
import springboot.applicationdata.model.PendingIndvIncomeDTO;


@Mapper
public interface PendingIndvIncomeMapper {

	PendingIndvIncome incomeDtoToDom(PendingIndvIncomeDTO dto);

	PendingIndvIncomeDTO incomeDomToDto(PendingIndvIncome indv);

}
