package springboot.applicationdata.mappers;

import org.mapstruct.Mapper;

import springboot.applicationdata.entities.PendingIndividual;
import springboot.applicationdata.model.PendingIndividualDTO;



@Mapper
public interface PendingIndividualMapper {

	PendingIndividual individualDtoToDom(PendingIndividualDTO dto);

	PendingIndividualDTO individualDomToDto(PendingIndividual indv);

}
