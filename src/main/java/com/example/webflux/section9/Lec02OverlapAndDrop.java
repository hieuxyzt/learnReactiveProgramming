package com.example.webflux.section9;

import com.example.webflux.courseUtil.Util;
import io.netty.buffer.ByteBufAllocator;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec02OverlapAndDrop {
    public static void main(String[] args) {
        evenStream()
                .buffer(3)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Flux<String> evenStream() {
        return Flux.interval(Duration.ofMillis(300))
                .map(i -> "event" + i);
    }
}
