package com.example.webflux;

import com.example.webflux.section9_batching.helper.BookOrder;
import org.junit.jupiter.api.Assertions;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec04AssertTest {
    public static void main(String[] args) {
        Mono<BookOrder> mono = Mono.fromSupplier(() -> new BookOrder())
                .delayElement(Duration.ofSeconds(3));

        StepVerifier.create(mono)
                .assertNext(b -> Assertions.assertNotNull(b.getAuthor()))
                .expectComplete()
                .verify(Duration.ofSeconds(4));
    }
}
