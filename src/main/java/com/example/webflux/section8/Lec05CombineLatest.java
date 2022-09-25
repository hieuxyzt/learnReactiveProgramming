package com.example.webflux.section8;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 *  See if something change
 *  Show result which is combined by add items.
 */
@Slf4j
public class Lec05CombineLatest {
    public static void main(String[] args) {
        Flux.combineLatest(getString(), getNumber(), (s, i) -> s + i)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(40);
    }

    private static Flux<String> getString() {
        return Flux.just("A", "B", "C", "D")
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(s -> log.info("String: {}", s));
    }

    private static Flux<Integer> getNumber() {
        return Flux.just(1, 2, 3)
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(i -> log.info("Integer: {}", i));
    }
}
