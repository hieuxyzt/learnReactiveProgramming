package com.example.webflux.section9_batching;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec04Window {
    public static void main(String[] args) {
        eventStream()
                .window(5)
                .flatMap(flux -> saveEvents(flux))
                .subscribe(Util.subscriber());
        Util.sleepSeconds(60);
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "event" + i);
    }

    private static Mono<Void> saveEvents(Flux<String> flux) {
        return flux
                .doOnNext(e -> System.out.println("saving" + e))
                .doOnComplete(() -> System.out.println("saved this batch"))
                .then();
    }
}
