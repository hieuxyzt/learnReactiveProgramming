package com.example.webflux.section8;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Quiz {
    public static void main(String[] args) {
//        Flux<String> flux = Flux.just("a", "b", "c");
//        flux.startWith(flux)
//                .subscribe(o -> System.out.println("Received: " + o));


        // just truong hop dac biet create 1
        // Duration se thuc hien truoc next(item). Tru truong hop chi co 1 item
        Mono<Integer> mono1 = Mono.just(1).delayElement(Duration.ofSeconds(1));
        Flux<Integer> flux1 = Flux.just(2);
        Flux<Integer> flux2 = flux1.map(i -> i + 1).delayElements(Duration.ofMillis(500));

//        Flux.merge(mono1, flux1, flux2)
//                        .subscribe(Util.subscriber());

        Flux<Integer> streamFlux = Flux.generate(
                () -> 1,
                (counter, integerSynchronousSink) -> {
                    integerSynchronousSink.next(counter);
                    return counter + 1;
                });
        streamFlux.delayElements(Duration.ofSeconds(10)).subscribe(Util.subscriber());

        Util.sleepSeconds(100);
    }
}
