package com.example.webflux.sec04;

import reactor.core.publisher.Flux;

public class Quiz {
    public static void main(String[] args) {
        Flux<Integer> range = Flux.range(1, 10);
        range.map(i -> i * 10);
        range.subscribe(System.out::println);

        Flux.range(1, 10)
                .filter(i -> i > 5)
                .take(2)
                .next()
                .subscribe(System.out::println);
    }
}
