package com.example.webflux.section8;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec04Zip {
    public static void main(String[] args) {
        Flux.zip(getBody(), getEngine(), getTires())
                .doOnNext(tuple3 -> System.out.println(tuple3.getT3()))
                .subscribe(Util.subscriber());
    }

    private static Flux<String> getBody() {
        return Flux.range(1, 5)
                .map(i -> "body");
    }

    private static Flux<String> getEngine() {
        return Flux.range(1, 2)
                .map(i -> "engine");
    }

    private static Flux<String> getTires() {
        return Flux.range(1, 6)
                .map(i -> "tires");
    }
}

