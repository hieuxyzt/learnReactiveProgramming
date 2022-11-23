package com.example.webflux.section10_repeat_retry;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Lec01Repeat {
    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        getIntegers()
                .repeat(() -> atomicInteger.get() < 10)
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> getIntegers() {
        return Flux.range(0, 2)
                .doOnSubscribe(s -> log.info("Subscribed"))
                .doOnComplete(() -> log.info("--Completed"))
                .map(i -> atomicInteger.getAndIncrement());
    }
}
