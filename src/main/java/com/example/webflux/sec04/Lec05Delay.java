package com.example.webflux.sec04;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05Delay {
    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.x", "7");
        Flux.range(1, 100) // request 32??
                .log()
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }
}
