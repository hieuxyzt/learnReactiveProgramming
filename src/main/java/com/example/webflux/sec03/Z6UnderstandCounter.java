package com.example.webflux.sec03;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class Z6UnderstandCounter {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            int count = 10;
            for (int i = 0; i < count; i++) {
                fluxSink.next(Util.faker().name().fullName());
            }
            fluxSink.complete();
        }).subscribe(Util.subscriber());

        System.out.println("===================================================");

        AtomicInteger count = new AtomicInteger(0);
        Flux.generate(synchronousSink -> {
            synchronousSink.next(Util.faker().name().fullName());
            if (count.getAndIncrement() >= 10) {
                synchronousSink.complete();
            }
        }).subscribe(Util.subscriber());

        System.out.println("=========================STATE======================");

        Flux.generate(
                () -> 0,
                (counter, sink) -> {
                    sink.next(Util.faker().name().fullName());
                    if (counter >= 10) {
                        sink.complete();
                    }
                    return counter + 1;
                }
        ).subscribe(Util.subscriber());
    }
}
