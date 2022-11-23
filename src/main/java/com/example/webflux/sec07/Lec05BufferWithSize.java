package com.example.webflux.sec07;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.util.concurrent.Queues;

@Slf4j
public class Lec05BufferWithSize {
    public static void main(String[] args) {
//        Queues
        System.setProperty("reactor.bufferSize.small", "16");
        Flux.create(fluxSink -> {
                    for (int i = 0; i < 100 && !fluxSink.isCancelled(); i++) {
                        fluxSink.next(i);
                        System.out.println("Pushed: " + i);
                        Util.sleepMillis(1);
                    }
                    fluxSink.complete();
                })
                .onBackpressureBuffer(50)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> Util.sleepMillis(10))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }
}
