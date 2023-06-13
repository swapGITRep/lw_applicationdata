package springboot.applicationdata.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import springboot.applicationdata.model.PendingIndividualDTO;
import springboot.applicationdata.services.PendingIndividualService;


@Slf4j
@RequiredArgsConstructor
@RestController
public class PendingIndividualController {

    public static final String INDIVIDUL_PATH = "/api/v1/individual";
    public static final String INDVIDUAL_PATH_ID = INDIVIDUL_PATH + "/{individualId}";
    
    

    private final PendingIndividualService pendingIndividualService;

	/*
	 * @PatchMapping(INDIVIDUL_PATH) public ResponseEntity<HttpStatus>
	 * updateBeerPatchById(@PathVariable("beerId")UUID beerId, @RequestBody BeerDTO
	 * beer){ beerService.patchBeerById(beerId, beer); return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT); }
	 * 
	 * @DeleteMapping(INDIVIDUL_PATH) public ResponseEntity<HttpStatus>
	 * deleteById(@PathVariable("beerId") UUID beerId){ if(Boolean.FALSE.equals(
	 * beerService.deleteById(beerId))){ throw new NotFoundException(); } return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT); }
	 * 
	 * @PutMapping(INDIVIDUL_PATH) public ResponseEntity<HttpStatus>
	 * updateById(@PathVariable("beerId")UUID beerId, @Validated @RequestBody
	 * BeerDTO beer){ if( beerService.updateBeerById(beerId, beer).isEmpty()){ throw
	 * new NotFoundException(); } return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT); }
	 */
    
    

    @PostMapping(INDIVIDUL_PATH)
    public ResponseEntity<HttpStatus> handlePost(@Validated @RequestBody PendingIndividualDTO individualDTO){
    	PendingIndividualDTO savedIndividualDTO = pendingIndividualService.saveNewIndividual(individualDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", INDIVIDUL_PATH + "/" + savedIndividualDTO.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = INDIVIDUL_PATH)
    public Page<PendingIndividualDTO> listIndividuals(@RequestParam(required = false) String lastName,
                                   @RequestParam(required = false) String applicationNumber,
                                   @RequestParam(required = false) Integer pageNumber,
                                   @RequestParam(required = false) Integer pageSize){
        return pendingIndividualService.listIndividuals(lastName, applicationNumber, pageNumber, pageSize);
    }


    @GetMapping(value = INDVIDUAL_PATH_ID)
    public PendingIndividualDTO getIndividualById(@PathVariable("individualId") UUID individualId){
        log.debug("Get Beer by Id - in controller");
        return pendingIndividualService.getIndividualById(individualId).orElseThrow(NotFoundException::new);
    }

}
