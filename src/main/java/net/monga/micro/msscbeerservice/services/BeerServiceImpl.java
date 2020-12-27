package net.monga.micro.msscbeerservice.services;


import lombok.RequiredArgsConstructor;
import net.monga.micro.msscbeerservice.domain.Beer;
import net.monga.micro.msscbeerservice.repositories.BeerRepository;
import net.monga.micro.msscbeerservice.web.controller.NotFoundException;
import net.monga.micro.msscbeerservice.web.mappers.BeerMapper;
import net.monga.micro.msscbeerservice.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @Override
    public BeerDto getById(UUID beerId) {

        return beerMapper.beerToBeerDto(
                beerRepository.findById(beerId).orElseThrow(NotFoundException :: new)
        );
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {

            Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException :: new);
            beer.setBeerName(beerDto.getBeerName());
            beer.setBeerStyle(beerDto.getBeerStyle().name());
            beer.setPrice(beerDto.getPrice());
            beer.setUpc(beerDto.getUpc());

            return beerMapper.beerToBeerDto(beerRepository.save(beer));

    }
}
