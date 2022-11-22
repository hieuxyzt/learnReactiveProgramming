package com.example.webflux.sec01;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class TestLazyPipeline {
    public static void main(String[] args) {
        Mono<String> publisher = Mono.fromSupplier(() -> getName("test1"))
                .log()
                .map(String::toUpperCase);

        publisher
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(Util.subscriber());

        Mono.just(getName("test2"));
        Util.sleepSeconds(3);
    }

    private static String getName(String name) {
        System.out.println("Generating name..");
        Util.sleepSeconds(2);
        return name.toUpperCase();
    }
}
