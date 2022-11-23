package com.example.webflux.section10_repeat_retry;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Lec02Retry {
    private static AtomicInteger atomicInteger = new AtomicInteger(1);
    public static void main(String[] args) {
        publisher()
                .retry(2)
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> publisher() {
        return Flux.range(0, 3)
                .map(i -> i / 0)
                .doOnError(throwable -> log.info("Error message: {}", throwable.getMessage()));
    }
}
