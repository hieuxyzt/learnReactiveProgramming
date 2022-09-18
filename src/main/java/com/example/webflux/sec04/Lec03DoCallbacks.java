package com.example.webflux.sec04;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Lec03DoCallbacks {
    public static void main(String[] args) {

        Flux.create(fluxSink -> {
            log.info("inside create");

            for (int i = 0; i < 5; i++) {
                fluxSink.next(i);
            }
            fluxSink.error(new RuntimeException("oops"));
//            fluxSink.complete();

            log.info("after create");
        })
                .doOnComplete(() -> log.info("doOnComplete"))
                .doFirst(() -> log.info("doFirst 1"))
                .doOnNext(o -> log.info("doOnNext: {}", o))
                .doOnSubscribe(s -> log.info("doOnSubscribe 1: {}", s))
                .doOnRequest(value -> log.info("doOnRequest 1: {}", value))
                .doFirst(() -> log.info("doFirst 2"))
                .doOnError(err -> log.info("doOnError: {}", err.getMessage()))
                .doOnTerminate(() -> log.info("doOnTerminate"))
                .doOnCancel(() -> log.info("doOnCancel"))
                .doFinally(signalType -> log.info("doFinally 1: {}", signalType))
                .doFirst(() -> log.info("doFirst 3"))
                .doOnDiscard(Object.class, o -> log.info("doOnDiscard: {}", o))
                .doOnRequest(value -> log.info("doOnRequest 2: {}", value))
                .doOnSubscribe(s -> log.info("doOnSubscribe 2: {}", s))
                .take(2)
                .doFinally(signalType -> log.info("doFinally 2: {}", signalType))
                .subscribe(Util.subscriber());
    }
}
