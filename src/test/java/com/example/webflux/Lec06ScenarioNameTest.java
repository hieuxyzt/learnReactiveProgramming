package com.example.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class Lec06ScenarioNameTest {
    @Test
    void test1() {
        Flux<String> flux = Flux.just("a", "b", "c");

        StepVerifierOptions senarioname = StepVerifierOptions.create().scenarioName("alphabets-test");

        StepVerifier
//                .create(flux)
                .create(flux, senarioname)
                .expectNextCount(12)
                .verifyComplete();
    }

    @Test
    void test2() {
        Flux<String> flux = Flux.just("a", "b1", "c");

        StepVerifier
                .create(flux)
                .expectNext("a")
                .as("a-test")
                .expectNext("b")
                .as("b-test")
                .expectNext("c")
                .as("c-test")
                .verifyComplete();
    }
}
