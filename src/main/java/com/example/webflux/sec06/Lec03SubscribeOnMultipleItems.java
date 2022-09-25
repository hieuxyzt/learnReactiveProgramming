package com.example.webflux.sec06;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Lec03SubscribeOnMultipleItems {
    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(objectFluxSink -> {
                    log.info("Create");
                    for (int i = 0; i < 20; i++) {
                        objectFluxSink.next(i);
                        Util.sleepSeconds(1);
                    }
                    objectFluxSink.complete();
                })
                .doOnNext(i -> printThreadName("next " + i));

//        Runnable runnable = () -> flux
//                .doFirst(() -> printThreadName("fist2"))
//                .subscribeOn(Schedulers.parallel())
//                .subscribe(v -> printThreadName("sub " + v));
//
//        for (int i = 0; i < 5; i++) {
//            new Thread(runnable).start();
//        }

        flux
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(v -> printThreadName("sub " + v));
        Util.sleepSeconds(3);
        flux
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(v -> printThreadName("sub " + v));
        Util.sleepSeconds(10);
    }

    private static void printThreadName(String msg) {
        log.info("[{}]\t{}", Thread.currentThread().getName(), msg);
    }
}
