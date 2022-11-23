package com.example.webflux.section11_sink;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class Lec05SinkMulticastDirectOrNothing {
    public static void main(String[] args) {
        System.getProperty("reactor.bufferSize.small", "16");
        Sinks.Many<Object> sink = Sinks.many().multicast().directBestEffort();

        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));

        for (int i = 0; i < 100; i++) {
            sink.tryEmitNext(i);
        }

        Util.sleepSeconds(10);
    }
}
