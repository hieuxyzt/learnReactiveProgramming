package com.example.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec05VirtualTimeTest {
    @Test
    void test1(){
        StepVerifier.withVirtualTime(() -> timeConsumingFlux())
                .thenAwait(Duration.ofSeconds(4))
                .expectNext("1a", "2a", "3a", "4a")
                .verifyComplete();

    }

    @Test
    void test2(){
        StepVerifier.withVirtualTime(() -> timeConsumingFlux())
                .expectSubscription()
                .expectNoEvent(Duration.ofMillis(500))
                .thenAwait(Duration.ofSeconds(4))
                .expectNext("1a", "2a", "3a", "4a")
                .verifyComplete();
    }

    private Flux<String> timeConsumingFlux() {
        return Flux.range(1, 4)
                .delayElements(Duration.ofSeconds(1))
                .map(i -> i + "a");
    }
}
