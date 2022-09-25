package com.example.webflux.sec06;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Lec02SubscribeOnDemo {
    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(fluxSink -> {
                    log.info("Create");
                    fluxSink.next(1);
                })
                // The one closer to the publisher ís precedence
                .subscribeOn(Schedulers.newParallel("vins"))
                .doOnNext(i -> printThreadName("next " + i));

        Runnable runnable = () -> flux
                .doFirst(() -> printThreadName("first2"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> printThreadName("first1"))
                .subscribe(v -> printThreadName("sub " + v));

        for (int i = 0; i < 2; i++) {
            new Thread(runnable).start();
        }
        Util.sleepSeconds(5);
    }



    private static void printThreadName(String msg){
        log.info("[{}] \t {}", Thread.currentThread().getName(), msg);
    }
}
