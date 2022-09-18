package com.example.webflux.sec04;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06OnError {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .log()
                .map(i -> 10 / (5 - i))
                //Like an ack
//                .onErrorReturn(-1)
//                .onErrorResume(e -> fallback())
                .onErrorContinue((throwable, o) -> {

                })
                .doFinally(signalType -> System.out.println("final: " + signalType))
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 200));
    }
}
