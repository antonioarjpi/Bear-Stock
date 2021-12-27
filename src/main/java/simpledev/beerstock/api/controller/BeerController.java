package simpledev.beerstock.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import simpledev.beerstock.api.dto.BeerDTO;
import simpledev.beerstock.api.dto.QuantityDTO;
import simpledev.beerstock.domain.exception.BeerAlreadyRegisteredException;
import simpledev.beerstock.domain.exception.BeerNotFoundException;
import simpledev.beerstock.domain.exception.BeerStockExceededException;
import simpledev.beerstock.domain.service.BeerService;

import javax.validation.Valid;
import java.util.List;

public class BeerController {

    private BeerService beerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeerDTO createBeer(@RequestBody @Valid BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
        return beerService.createBeer(beerDTO);
    }

    @GetMapping("/{name}")
    public BeerDTO findByName(@PathVariable String name) throws BeerNotFoundException {
        return beerService.findByName(name);
    }

    @GetMapping
    public List<BeerDTO> listBeers() {
        return beerService.listAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws BeerNotFoundException {
        beerService.deleteById(id);
    }

    @PatchMapping("/{id}/increment")
    public BeerDTO increment(@PathVariable Long id, @RequestBody @Valid QuantityDTO quantityDTO) throws BeerNotFoundException, BeerStockExceededException {
        return beerService.increment(id, quantityDTO.getQuantity());
    }

}
