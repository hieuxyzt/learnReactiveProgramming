package com.example.webflux.sec02;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class Lec09FluxFromMono {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("a");
        Flux<String> flux = Flux.from(mono);

        doSomething(flux);
//        flux
//                .log()
//                .subscribe(Util.onNext());

        Flux.range(1, 10)
                .filter(i -> i > 3)
                .next()
                .subscribe(Util.onNext(),
                        Util.onError(),
                        Util.onComplete());
    }

    private static void doSomething (Flux<String> flux){
        //do something
    }
}
