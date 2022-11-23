package com.example.webflux.sec06;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
// Do first2 callback -> subscribeOn (change thread) onSubscribe() -> first1 -> doOnNext1 next(), doOnNext2 (next)
// Vì Lazy nên khi subscribe nó mới thực hiện generate data -> generate dong thoi o changed thread
public class Z0SubscribeOn {
    public static void main(String[] args) {
        Flux.range(0, 2)
                .log()
                .doFirst(() -> log.info("do First 1 wtf"))
                .doOnNext(integer -> log.info("do on next 1: {}", integer))
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(integer -> log.info("do on next 2: {}", integer))
                .doFirst(() -> log.info("do First 2 wtf"))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(1);
    }
}
