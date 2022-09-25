package com.example.webflux.sec06;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Lec07FluxInterval {
    public static void main(String[] args) {
        Flux.interval(Duration.ofSeconds(1)) //implicit use Scheduler parallel
                .subscribeOn(Schedulers.boundedElastic()) //Does not change anything
                .subscribe(Util.subscriber());


        Util.sleepSeconds(10);
    }
}
