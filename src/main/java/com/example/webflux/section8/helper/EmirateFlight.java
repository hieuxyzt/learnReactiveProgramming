package com.example.webflux.section8.helper;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class EmirateFlight {
    public static Flux<String> getFlight() {
        return Flux.range(1, Util.faker().random().nextInt(1, 8))
                .map(i -> "Emirate" + Util.faker().random().nextInt(100, 999))
                .filter(i -> Util.faker().random().nextBoolean())
                .doOnNext(i -> System.out.println(Thread.currentThread().getName()))
                .delayElements(Duration.ofSeconds(1));
    }
}
