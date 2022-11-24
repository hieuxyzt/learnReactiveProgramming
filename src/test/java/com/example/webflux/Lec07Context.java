package com.example.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

public class Lec07Context {
    @Test
    void test1() {
        StepVerifier.create(getWelcomeMessage())
                .verifyError(RuntimeException.class);
    }

    @Test
    void test2() {
        StepVerifierOptions options = StepVerifierOptions.create().withInitialContext(Context.of("user", "hieunn"));

        StepVerifier.create(getWelcomeMessage(), options)
                .expectNext("Welcome hieunn")
                .verifyComplete();
    }

    private Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(contextView -> {
            if(contextView.hasKey("user")) {
                return Mono.just("Welcome " + contextView.get("user"));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }
}
