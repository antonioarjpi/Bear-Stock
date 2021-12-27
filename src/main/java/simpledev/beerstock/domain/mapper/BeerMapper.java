package simpledev.beerstock.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import simpledev.beerstock.api.dto.BeerDTO;
import simpledev.beerstock.domain.model.Beer;

@Mapper
public interface BeerMapper {

    BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

    Beer toModel(BeerDTO beerDTO);

    BeerDTO toDTO(Beer beer);
}
