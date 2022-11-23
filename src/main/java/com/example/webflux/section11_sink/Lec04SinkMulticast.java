package com.example.webflux.section11_sink;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec04SinkMulticast {
    public static void main(String[] args) {
        Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));


        sink.tryEmitNext("hello");
        sink.tryEmitNext("w");
        sink.tryEmitNext("t");
        sink.tryEmitNext("f");
        sink.tryEmitComplete();
    }
}
