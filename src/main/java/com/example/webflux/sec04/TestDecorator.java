package com.example.webflux.sec04;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class TestDecorator {
    public static void main(String[] args) {
        Flux.range(1, 20)
                .log()
                .handle((integer, synchronousSink) -> {
                    if (integer == 6) {
                        synchronousSink.complete();
                        return;
                    }
                    synchronousSink.next(integer);

                }).subscribe(Util.subscriber());

        System.out.println("===========================================================");

        Flux.generate(synchronousSink -> synchronousSink.next(Util.faker().name().fullName()))
                .handle((o, synchronousSink) -> {
                    String result = ((String)o).toUpperCase();
                    synchronousSink.next(result);
                    synchronousSink.complete();
                })
                .subscribe(Util.subscriber());

        System.out.println("===========================================================");

        Flux.range(1, 20)
                .transform(transformToNameFunction())
                .subscribe(Util.subscriber());

        System.out.println("===========================================================");

        Flux.range(0, 10)
                .switchOnFirst((signal, flux) -> {
                    if(signal.get() == 0) {
                        return Mono.error(new Exception("OH SHIT WTF"));
                    }
                    return flux;
                })
                .subscribe(Util.subscriber());

        System.out.println("===========================================================");

        Mono<Void> m = Mono.just("abc").then(Mono.fromRunnable(() -> System.out.println("WHATTTT")));
        Mono<Void> v = m.then(Mono.fromRunnable(() -> System.out.println("WHAT SUP FUCKER")));
        v.subscribe(Util.subscriber());

        Util.sleepSeconds(1);

        System.out.println("===========================================================");
        Flux.range(0, 10)
                .flatMap(flapMapFunction())
                .subscribe(Util.subscriber());
    }

    private static Function<Flux<Integer>, Flux<String>> transformToNameFunction() {
        return flux -> Flux.range(0, 10).filter(i -> i % 2 == 0)
                .map(i -> Util.faker().name().fullName());
    }

    private static Function<Integer, Flux<String>> flapMapFunction() {
        return integer -> Flux.range(0, integer)
                .map(i -> Util.faker().name().fullName());
    }
}
