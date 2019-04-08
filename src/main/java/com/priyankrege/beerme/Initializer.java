package com.priyankrege.beerme;

import com.priyankrege.beerme.model.Beer;
import com.priyankrege.beerme.repository.BeerRepository;
import com.priyankrege.beerme.values.AlchoholContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class Initializer implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(Initializer.class);

    @Autowired
    private BeerRepository beerRepository;

    public void setBeerRepository(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String[] strings) throws Exception {

        log.info("Initializing values");

        Beer beer1 = Beer.builder().name("Shiner").price((float)10).alchoholContent(AlchoholContent.MILD).build();
        Beer beer2 = Beer.builder().name("Dos Equis").price((float)7.25).alchoholContent(AlchoholContent.STRONG).build();
        Beer beer3 = Beer.builder().name("Revolver").price((float)11.25).alchoholContent(AlchoholContent.POISON).build();
        Beer beer4 = Beer.builder().name("Kingfisher").price((float)8).alchoholContent(AlchoholContent.VERY_STRONG).build();
        Beer beer5 = Beer.builder().name("Coors Light").price((float)4.25).alchoholContent(AlchoholContent.LIGHT).build();

        Stream.of(beer1,beer2,beer3,beer4,beer5).forEach(beer -> beerRepository.save(beer));

        beerRepository.findAll().forEach(System.out::println);

    }
}
