package com.example.webflux.sec04;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec08DefaultIfEmpty {
    public static void main(String[] args) {
        getOrderNumber()
                .filter(i -> i > 10)
                .defaultIfEmpty(-100)
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> getOrderNumber() {
        return Flux.range(1, 10);
    }
}
