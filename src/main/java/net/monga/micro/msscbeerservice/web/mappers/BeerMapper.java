package net.monga.micro.msscbeerservice.web.mappers;

import net.monga.micro.msscbeerservice.domain.Beer;
import net.monga.micro.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto dto);
}
