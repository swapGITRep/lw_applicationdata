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
import springboot.applicationdata.entities.PendingIndvIncome;
import springboot.applicationdata.mappers.PendingIndvIncomeMapper;
import springboot.applicationdata.model.PendingIndvIncomeDTO;
import springboot.applicationdata.repositories.PendingIndividualRepository;
import springboot.applicationdata.repositories.PendingIndvIncomeRepository;


@Service
@Primary
@RequiredArgsConstructor
public class PendingIndvIncomeServiceJPA implements PendingIndvIncomeService {
    
	private final PendingIndividualRepository applicationIndividualRepository;
	
	private final PendingIndvIncomeRepository pendingIndvIncomeRepository;
    
  
    //private final PendingIndividualMapper applicationIndividualMapper;
 
	 private final PendingIndvIncomeMapper pendingIndvIncomeMapper;

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 25;

   
	public Page<PendingIndvIncome> listIncomeByLastName(String lastName, Pageable pageable) {
		return pendingIndvIncomeRepository.findAllByApplicationIndividualLastNameIsLikeIgnoreCase("%" + lastName + "%",
				pageable);
	}
    
    @Override
    public Page<PendingIndvIncomeDTO> listIncomeByIndividual(String lastName, Integer pageNumber, Integer pageSize) {

        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);

        Page<PendingIndvIncome> incomePage;

        if(StringUtils.hasText(lastName)) {
        	incomePage = listIncomeByLastName(lastName, pageRequest);
        } else {
        	incomePage = pendingIndvIncomeRepository.findAll(pageRequest);
        }

        return incomePage.map(pendingIndvIncomeMapper::incomeDomToDto);

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

	
    @Override
	public Optional<PendingIndvIncomeDTO> getIncomeById(UUID id) {
		return Optional.ofNullable(pendingIndvIncomeMapper
				.incomeDomToDto(pendingIndvIncomeRepository.findById(id).orElse(null)));
	}
	
	@Override
	public PendingIndvIncomeDTO saveNewIncome(PendingIndvIncomeDTO incomeDTO) {
		PendingIndvIncome incomeDOM = pendingIndvIncomeMapper.incomeDtoToDom(incomeDTO);

		incomeDOM.setApplicationIndividual(applicationIndividualRepository
				.findById(incomeDTO.getApplicationIndividual().getId()).orElseThrow());
		
		return pendingIndvIncomeMapper
				.incomeDomToDto(pendingIndvIncomeRepository.save(incomeDOM));
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
