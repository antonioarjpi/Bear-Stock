package simpledev.beerstock.service;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import simpledev.beerstock.builder.BeerDTOBuilder;
import simpledev.beerstock.dto.BeerDTO;
import simpledev.beerstock.exception.BeerAlreadyRegisteredException;
import simpledev.beerstock.mapper.BeerMapper;
import simpledev.beerstock.model.Beer;
import simpledev.beerstock.repository.BeerRepository;

import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTest {

    private static final long INVALID_BEER_ID = 1L;

    @Mock
    private BeerRepository beerRepository;

    private BeerMapper beerMapper = BeerMapper.INSTANCE;

    @InjectMocks
    private BeerService beerService;

    @Test
    void whenBeerInformedTheIShuldBeCreated() throws BeerAlreadyRegisteredException {
        BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer expectedSavedBeer = beerMapper.toModel(beerDTO);

        when(beerRepository.findByName(beerDTO.getName())).thenReturn(Optional.empty());
        when(beerRepository.save(expectedSavedBeer)).thenReturn(expectedSavedBeer);

        BeerDTO createdBeerDTO = beerService.createBeer(beerDTO);

        assertThat(createdBeerDTO.getId(), Matchers.is(Matchers.equalTo(beerDTO.getId())));
        assertThat(createdBeerDTO.getName(), Matchers.is(Matchers.equalTo(beerDTO.getName())));
        assertThat(createdBeerDTO.getQuantity(), Matchers.is(Matchers.equalTo(beerDTO.getQuantity())));


    }
}
