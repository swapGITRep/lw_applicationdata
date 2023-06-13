package springboot.applicationdata.services;

import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import springboot.applicationdata.model.BeerCSVRecord;
import springboot.applicationdata.services.BeerCsvService;
import springboot.applicationdata.services.BeerCsvServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BeerCsvServiceImplTest {

    BeerCsvService beerCsvService = new BeerCsvServiceImpl();

    @Test
    void convertCSV() throws FileNotFoundException {

        File file = ResourceUtils.getFile("classpath:csvdata/beers.csv");

        List<BeerCSVRecord> recs = beerCsvService.convertCSV(file);

        System.out.println(recs.size());

        assertThat(recs.size()).isGreaterThan(0);
    }
}