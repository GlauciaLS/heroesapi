package com.digitalinnovationone.heroesapi.controller;

import com.digitalinnovationone.heroesapi.document.Heroes;
import com.digitalinnovationone.heroesapi.repository.HeroesRepository;
import com.digitalinnovationone.heroesapi.service.HeroesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.digitalinnovationone.heroesapi.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RestController
public class HeroesController {

    private static final Logger log = LoggerFactory.getLogger(HeroesController.class);

    HeroesService heroesService;
    HeroesRepository heroesRepository;

    public HeroesController(HeroesService heroesService, HeroesRepository heroesRepository) {
        this.heroesRepository = heroesRepository;
        this.heroesService = heroesService;
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL)
    public Flux<Heroes> getAllItems() {
        log.info("Requesting the list of all heroes.");
        return heroesService.findAll();
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
    public Mono<ResponseEntity<Heroes>> findHeroById(@PathVariable String id) {
        log.info("Requesting the hero by id {}.", id);
        return heroesService.findById(id)
                .map((item)-> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes heroes) {
        log.info("Request to create a new hero.");
        return heroesService.save(heroes);
    }

    @DeleteMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
    @ResponseStatus(code = HttpStatus.CONTINUE)
    public Mono<HttpStatus> deleteHeroById(@PathVariable String id) {
        heroesService.deleteByIdHero(id);
        log.info("Request to delete a hero by id {}.", id);
        return Mono.just(HttpStatus.CONTINUE);
    }

}
