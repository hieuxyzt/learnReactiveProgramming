package com.example.webflux.section11_sink;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec02SinkUnicast {
    public static void main(String[] args) {

        //Handler
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Object> flux = sink.asFlux();
        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));

        for (int i = 0; i < 10; i++) {
            sink.tryEmitNext("hi sam " + i);
        }
        sink.tryEmitComplete();
    }
}
