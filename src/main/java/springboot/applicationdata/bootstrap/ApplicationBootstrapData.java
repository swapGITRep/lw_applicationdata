package springboot.applicationdata.bootstrap;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import springboot.applicationdata.entities.PendingIndividual;
import springboot.applicationdata.entities.PendingApplication;
import springboot.applicationdata.model.AppStatus;
import springboot.applicationdata.repositories.PendingIndividualRepository;
import springboot.applicationdata.repositories.PendingApplicationRepository;

/**
 * Created by jt, Spring Framework Guru.
 */
@Component
@RequiredArgsConstructor
public class ApplicationBootstrapData implements CommandLineRunner {
    private final PendingApplicationRepository pendingApplicationRepository;
    private final PendingIndividualRepository applicationIndividualRepository;
    //private final BeerCsvService beerCsvService;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        loadPendingAppData();
        //loadCsvData();
        //loadApplicationIndvData();
    }

    /*
    private void loadCsvData() throws FileNotFoundException {
        if (beerRepository.count() < 10){
            File file = ResourceUtils.getFile("classpath:csvdata/beers.csv");

            List<BeerCSVRecord> recs = beerCsvService.convertCSV(file);

            recs.forEach(beerCSVRecord -> {
                BeerStyle beerStyle = switch (beerCSVRecord.getStyle()) {
                    case "American Pale Lager" -> BeerStyle.LAGER;
                    case "American Pale Ale (APA)", "American Black Ale", "Belgian Dark Ale", "American Blonde Ale" ->
                            BeerStyle.ALE;
                    case "American IPA", "American Double / Imperial IPA", "Belgian IPA" -> BeerStyle.IPA;
                    case "American Porter" -> BeerStyle.PORTER;
                    case "Oatmeal Stout", "American Stout" -> BeerStyle.STOUT;
                    case "Saison / Farmhouse Ale" -> BeerStyle.SAISON;
                    case "Fruit / Vegetable Beer", "Winter Warmer", "Berliner Weissbier" -> BeerStyle.WHEAT;
                    case "English Pale Ale" -> BeerStyle.PALE_ALE;
                    default -> BeerStyle.PILSNER;
                };

                beerRepository.save(Beer.builder()
                                .beerName(StringUtils.abbreviate(beerCSVRecord.getBeer(), 50))
                                .beerStyle(beerStyle)
                                .price(BigDecimal.TEN)
                                .upc(beerCSVRecord.getRow().toString())
                                .quantityOnHand(beerCSVRecord.getCount())
                        .build());
            });
        }
    }
    */

    private void loadPendingAppData() {
        if (pendingApplicationRepository.count() == 0){
        	
        	PendingApplication app1 = PendingApplication.builder()
                    .applicationNumber("1234567890")
                    .appStatus(AppStatus.INPROGRESS)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();
        	
        	PendingApplication savedApp1 = pendingApplicationRepository.saveAndFlush(app1);
        	//savedApp1.setApplicationIndividual(Set.of(setIndvidual("Firstname 1", "Lastname 1")));
        	
        	
        	PendingApplication app2 = PendingApplication.builder()
                    .applicationNumber("5678934567")
                    .appStatus(AppStatus.INPROGRESS)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();

        	PendingApplication savedApp2 = pendingApplicationRepository.saveAndFlush(app2);
        	//savedApp2.setApplicationIndividual(Set.of(setIndvidual("Firstname 2", "Lastname 2")));

        	
        	PendingApplication app3 = PendingApplication.builder()
                    .applicationNumber("8434689654")
                    .appStatus(AppStatus.INPROGRESS)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();
        	
        	
        	PendingApplication savedApp3 = pendingApplicationRepository.saveAndFlush(app3);
        	//savedApp3.setApplicationIndividual(Set.of(setIndvidual("Firstname 3", "Lastname 3")));

        }

    }

    
    private PendingIndividual setIndvidual(String firstName, String lastName) {
    	
    	return PendingIndividual.builder()
                .id(UUID.randomUUID())
                .firstName(firstName)
                .lastName(lastName)
                .version(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }
    
    
    private void loadApplicationIndvData() {

        if (applicationIndividualRepository.count() == 0) {
        	PendingIndividual indv1 = PendingIndividual.builder()
                    .id(UUID.randomUUID())
                    .firstName("Firstname 1")
                    .lastName("Lastname 1")
                    .version(1)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();

        	PendingIndividual indv2 = PendingIndividual.builder()
                    .id(UUID.randomUUID())
                    .firstName("Firstname 2")
                    .lastName("Lastname 2")
                    .version(1)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();

        	PendingIndividual indv3 = PendingIndividual.builder()
                    .id(UUID.randomUUID())
                    .firstName("Firstname 3")
                    .lastName("Lastname 3")
                    .version(1)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();

        	applicationIndividualRepository.saveAll(Arrays.asList(indv1, indv2, indv3));
        }

    }


}
