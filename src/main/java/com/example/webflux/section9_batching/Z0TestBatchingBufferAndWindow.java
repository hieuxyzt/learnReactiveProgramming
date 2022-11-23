package com.example.webflux.section9_batching;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
public class Z0TestBatchingBufferAndWindow {
    public static void main(String[] args) {
        eventStream()
                .buffer(5)
                .subscribe(Util.subscriber());

//        eventStream()
//                .window(5)
//                .flatMap(Z0TestBatchingBufferAndWindow::saveEvents)
//                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "event" + i);
    }

    public static Mono<String> saveEvents(Flux<String> flux) {
        return flux
                .doOnNext(e -> log.info("saving : {}", e))
                .doOnComplete(() -> {
                    log.info("saved this batch");
                    log.info("===============================");
                })
                .then(Mono.just("saving complete"));
    }
}
