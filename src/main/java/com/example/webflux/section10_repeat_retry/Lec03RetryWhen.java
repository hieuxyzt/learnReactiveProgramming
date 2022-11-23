package com.example.webflux.section10_repeat_retry;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
public class Lec03RetryWhen {
    public static void main(String[] args) {
        publisher()
                .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(3)))
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> publisher() {
        return Flux.range(1, 3)
                .map(i -> i / (Boolean.TRUE.equals(Util.faker().random().nextBoolean()) ? 1 : 0))
                .doOnError(e -> log.info("error message: {}", e.getMessage()));
    }
}
