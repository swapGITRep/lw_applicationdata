package springboot.applicationdata.mappers;

import org.mapstruct.Mapper;

import springboot.applicationdata.entities.Customer;
import springboot.applicationdata.model.CustomerDTO;

/**
 * Created by jt, Spring Framework Guru.
 */
@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO dto);

    CustomerDTO customerToCustomerDto(Customer customer);

}
