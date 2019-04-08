package com.priyankrege.beerme.controller;

import com.priyankrege.beerme.model.Beer;
import com.priyankrege.beerme.model.BeerInterface;
import com.priyankrege.beerme.repository.BeerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class BeerController {

    private final Logger log = LoggerFactory.getLogger(BeerController.class);

    private BeerRepository beerRepository;

    @Autowired
    public void setBeerRepository(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @GetMapping("/all-beers")
    public Collection<Beer> getAllBeers (){
        return beerRepository.findAll();
    }

    @GetMapping("/beers/{type}")
    public ResponseEntity<?> getBeerByType (@PathVariable String type){

        log.info("Filtering beers");
        List<Optional<Beer>> beers =  beerRepository.findAll().stream().map(Optional::ofNullable).filter(beer -> filterByContent(beer.get(),type))
                .collect(Collectors.toList());

        return beers.size() > 0 ? ResponseEntity.ok().body(beers) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static boolean filterByContent(Beer b, String s){
        return b.getAlchoholContent().toString().equals(s);
    }

    private static boolean filterByPrice(Beer b, Float from, Float to){
        return b.getPrice() >= from && b.getPrice() <= to;
    }

    @GetMapping("/beers/from/{from}/to/{to}")
    public ResponseEntity<?> getBeersByPrice (@PathVariable Float from, @PathVariable Float to){

        log.info("Filtering by price");

        List<Optional<Beer>> beers =  beerRepository.findAll().stream().map(Optional::ofNullable).filter(beer -> filterByPrice(beer.get(), from, to))
                .collect(Collectors.toList());

        return beers.size() > 0 ? ResponseEntity.ok().body(beers) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
