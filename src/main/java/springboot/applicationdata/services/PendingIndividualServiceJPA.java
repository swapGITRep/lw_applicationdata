package springboot.applicationdata.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;
import springboot.applicationdata.entities.PendingIndividual;
import springboot.applicationdata.mappers.PendingIndividualMapper;
import springboot.applicationdata.model.PendingIndividualDTO;
import springboot.applicationdata.repositories.PendingIndividualRepository;
import springboot.applicationdata.repositories.PendingApplicationRepository;

@Service
@Primary
@RequiredArgsConstructor
public class PendingIndividualServiceJPA implements PendingIndividualService {
    private final PendingIndividualRepository applicationIndividualRepository;
    
    private final PendingApplicationRepository pendingApplicationRepository;
    
    private final PendingIndividualMapper applicationIndividualMapper;
    

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 25;

    @Override
    public Page<PendingIndividualDTO> listIndividuals(String lastName, String applicationNumber,
                                   Integer pageNumber, Integer pageSize) {

        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);

        Page<PendingIndividual> individualPage;

        if(StringUtils.hasText(lastName) && applicationNumber == null) {
        	individualPage = listIndividualByLastName(lastName, pageRequest);
        } else if (!StringUtils.hasText(lastName) && applicationNumber != null){
        	individualPage = listIndividualByPendingApplicationNum(applicationNumber, pageRequest);
        }
		/*
		 * else if (StringUtils.hasText(beerName) && beerStyle != null){ beerPage =
		 * listBeersByNameAndStyle(beerName, beerStyle, pageRequest); }
		 */ 
        
        else {
        	individualPage = applicationIndividualRepository.findAll(pageRequest);
        }

		/*
		 * if (showInventory != null && !showInventory) { beerPage.forEach(beer ->
		 * beer.setQuantityOnHand(null)); }
		 */

        return individualPage.map(applicationIndividualMapper::individualDomToDto);

    }

    public PageRequest buildPageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber;
        int queryPageSize;

        if (pageNumber != null && pageNumber > 0) {
            queryPageNumber = pageNumber - 1;
        } else {
            queryPageNumber = DEFAULT_PAGE;
        }

        if (pageSize == null) {
            queryPageSize = DEFAULT_PAGE_SIZE;
        } else {
            if (pageSize > 1000) {
                queryPageSize = 1000;
            } else {
                queryPageSize = pageSize;
            }
        }

        Sort sort = Sort.by(Sort.Order.asc("lastName"));

        return PageRequest.of(queryPageNumber, queryPageSize, sort);
    }

	/*
	 * private Page<Beer> listBeersByNameAndStyle(String beerName, BeerStyle
	 * beerStyle, Pageable pageable) { return
	 * beerRepository.findAllByBeerNameIsLikeIgnoreCaseAndBeerStyle("%" + beerName +
	 * "%", beerStyle, pageable); }
	 */

    public Page<PendingIndividual> listIndividualByPendingApplicationNum(String applicationNumber, Pageable pageable) {
        
    	return applicationIndividualRepository.findByPendingApplicationApplicationNumber(applicationNumber, pageable);
    }

    public Page<PendingIndividual> listIndividualByLastName(String lastName, Pageable pageable){
        return applicationIndividualRepository.findAllByLastNameIsLikeIgnoreCase("%" + lastName + "%", pageable);
    }

    @Override
	public Optional<PendingIndividualDTO> getIndividualById(UUID id) {
		return Optional.ofNullable(applicationIndividualMapper
				.individualDomToDto(applicationIndividualRepository.findById(id).orElse(null)));
	}
	
	@Override
	public PendingIndividualDTO saveNewIndividual(PendingIndividualDTO individualDTO) {
		PendingIndividual individualDOM = applicationIndividualMapper.individualDtoToDom(individualDTO);

		individualDOM.setPendingApplication(pendingApplicationRepository
				.findByApplicationNumber(individualDOM.getPendingApplication().getApplicationNumber()));
		return applicationIndividualMapper
				.individualDomToDto(applicationIndividualRepository.save(individualDOM));
	}

   /*
	@Override
    public Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer) {
        AtomicReference<Optional<BeerDTO>> atomicReference = new AtomicReference<>();

        beerRepository.findById(beerId).ifPresentOrElse(foundBeer -> {
            foundBeer.setBeerName(beer.getBeerName());
            foundBeer.setBeerStyle(beer.getBeerStyle());
            foundBeer.setUpc(beer.getUpc());
            foundBeer.setPrice(beer.getPrice());
            foundBeer.setQuantityOnHand(beer.getQuantityOnHand());
            atomicReference.set(Optional.of(beerMapper
                    .beerToBeerDto(beerRepository.save(foundBeer))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }
    */

    /*
    @Override
    public Boolean deleteById(UUID beerId) {
        if (beerRepository.existsById(beerId)) {
            beerRepository.deleteById(beerId);
            return true;
        }
        return false;
    }
    */

    /*
    @Override
    public Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beer) {
        AtomicReference<Optional<BeerDTO>> atomicReference = new AtomicReference<>();

        beerRepository.findById(beerId).ifPresentOrElse(foundBeer -> {
            if (StringUtils.hasText(beer.getBeerName())){
                foundBeer.setBeerName(beer.getBeerName());
            }
            if (beer.getBeerStyle() != null){
                foundBeer.setBeerStyle(beer.getBeerStyle());
            }
            if (StringUtils.hasText(beer.getUpc())){
                foundBeer.setUpc(beer.getUpc());
            }
            if (beer.getPrice() != null){
                foundBeer.setPrice(beer.getPrice());
            }
            if (beer.getQuantityOnHand() != null){
                foundBeer.setQuantityOnHand(beer.getQuantityOnHand());
            }
            atomicReference.set(Optional.of(beerMapper
                    .beerToBeerDto(beerRepository.save(foundBeer))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }
    */

	
}
