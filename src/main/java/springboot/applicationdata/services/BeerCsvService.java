package springboot.applicationdata.services;

import java.io.File;
import java.util.List;

import springboot.applicationdata.model.BeerCSVRecord;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface BeerCsvService {
    List<BeerCSVRecord> convertCSV(File csvFile);
}
