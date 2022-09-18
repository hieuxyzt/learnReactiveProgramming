package com.example.webflux.sec02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Lec02MultipleSubscribers {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4);

        Flux<Integer> evenFlux = flux.filter(i -> i % 2 == 0);


        flux.subscribe(i -> log.info("Sub 1 : {}", i));
        evenFlux.subscribe(i -> log.info("Sub 2 : {}", i));
    }
}
