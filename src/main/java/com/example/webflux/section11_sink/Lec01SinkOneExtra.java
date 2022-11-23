package com.example.webflux.section11_sink;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Lec01SinkOneExtra {
    public static void main(String[] args) {
        Sinks.One<String> sink = Sinks.one();
        Mono<String> mono = sink.asMono();

        mono.subscribe(Util.subscriber("sam"));
        mono.subscribe(Util.subscriber("mike"));

        sink.tryEmitValue("Hello");
    }
}
