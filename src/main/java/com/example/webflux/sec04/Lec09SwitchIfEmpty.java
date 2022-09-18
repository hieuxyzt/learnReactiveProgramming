package com.example.webflux.sec04;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec09SwitchIfEmpty {
    public static void main(String[] args) {
        getOrderNumbers()
                .filter(i -> i > 10)
                .switchIfEmpty(fallback())
                .subscribe(Util.subscriber());
    }

    //redis cache
    private static Flux<Integer> getOrderNumbers( ){
        return Flux.range(1, 9);
    }

    //database
    private static Flux<Integer> fallback() {
        return Flux.range(20, 5);
    }
}