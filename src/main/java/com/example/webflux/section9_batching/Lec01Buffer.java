package com.example.webflux.section9_batching;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec01Buffer {
    public static void main(String[] args) {
        // wait 5 items to emit
        eventStream()
                .bufferTimeout(5, Duration.ofSeconds(2))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(10))
//                .take(3)
                .map(i -> "event" + i);
    }

//    het 126, den 127 _ Time out
}
