package com.example.webflux.sec02;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec08FluxInterval {
    public static void main(String[] args) {
        //nonblocking, asynchronized
        Flux.interval(Duration.ofSeconds(1))
                .subscribe(Util.onNext());
        Util.sleepSeconds(5);

        //periodically send item to end user
    }
}
