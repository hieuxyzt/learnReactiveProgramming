package com.example.webflux.section9;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05Group {
    public static void main(String[] args) {
        Flux.range(1, 30)
                .delayElements(Duration.ofSeconds(1))
                .groupBy(i -> i % 2)
                .subscribe(flux -> process(flux, flux.key()));
        Util.sleepSeconds(20);
    }

    private static void process(Flux<Integer> flux, int key) {
        flux.subscribe(integer -> System.out.println("Key: " + key + ",Item: " + integer));
    }
}
