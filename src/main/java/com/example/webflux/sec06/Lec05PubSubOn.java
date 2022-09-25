package com.example.webflux.sec06;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Lec05PubSubOn {
    public static void main(String[] args) {
        // Mục đích của publishOn là để hoạt động asynchronously
        Flux<Object> flux = Flux.create(objectFluxSink -> {
                    log.info("Create");
                    for (int i = 0; i < 4; i++) {
                        objectFluxSink.next(i);
                    }
                    objectFluxSink.complete();
                })
                .doOnNext(i -> printThreadName("next " + i));

        flux
                .publishOn(Schedulers.parallel())
                .doOnNext(i -> printThreadName("next " + i))
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(i -> printThreadName("next " + i))
                .subscribe(v -> printThreadName("sub " + v));

        Util.sleepSeconds(10);
    }

    private static void printThreadName(String msg) {
        log.info("[{}]\t{}", Thread.currentThread().getName(), msg);
    }
}
