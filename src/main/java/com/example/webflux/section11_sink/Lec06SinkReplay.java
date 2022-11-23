package com.example.webflux.section11_sink;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
public class Lec06SinkReplay {
    public static void main(String[] args) {
        Sinks.Many<Object> sink = Sinks.many().replay().all();
        Flux<Object> flux = sink.asFlux();

        sink.tryEmitNext("1");
        sink.tryEmitNext("2");
        sink.tryEmitNext("3");

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));

        sink.tryEmitNext("?");
        flux.subscribe(Util.subscriber("jake"));

        sink.tryEmitNext("done");
    }
}
