package springboot.applicationdata.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import springboot.applicationdata.entities.Beer;
import springboot.applicationdata.entities.BeerOrder;
import springboot.applicationdata.entities.BeerOrderShipment;
import springboot.applicationdata.entities.Customer;
import springboot.applicationdata.repositories.BeerOrderRepository;
import springboot.applicationdata.repositories.BeerRepository;
import springboot.applicationdata.repositories.CustomerRepository;

@SpringBootTest
class BeerOrderRepositoryTest {

    @Autowired
    BeerOrderRepository beerOrderRepository;
;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BeerRepository beerRepository;

    Customer testCustomer;
    Beer testBeer;

    @BeforeEach
    void setUp() {
        testCustomer = customerRepository.findAll().get(0);
        testBeer = beerRepository.findAll().get(0);
    }

    @Transactional
    @Test
    void testBeerOrders() {
        BeerOrder beerOrder = BeerOrder.builder()
                .customerRef("Test order")
                .customer(testCustomer)
                .beerOrderShipment(BeerOrderShipment.builder()
                        .trackingNumber("1235r")
                        .build())
                .build();

        BeerOrder savedBeerOrder = beerOrderRepository.save(beerOrder);


        System.out.println(savedBeerOrder.getCustomerRef());
        System.out.println(savedBeerOrder);
    }
}











