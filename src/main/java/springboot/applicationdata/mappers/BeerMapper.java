package springboot.applicationdata.mappers;

import org.mapstruct.Mapper;

import springboot.applicationdata.entities.Beer;
import springboot.applicationdata.model.BeerDTO;

/**
 * Created by jt, Spring Framework Guru.
 */
@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);

}
