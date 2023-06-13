package springboot.applicationdata.mappers;

import org.mapstruct.Mapper;

import springboot.applicationdata.entities.PendingApplication;
import springboot.applicationdata.model.PendingApplicationDTO;


@Mapper
public interface PendingApplicationMapper {

	PendingApplication applicationDtoToDom(PendingApplicationDTO dto);

	PendingApplicationDTO applicationDomToindividualDto(PendingApplication indv);

}
