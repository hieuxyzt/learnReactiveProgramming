package com.example.webflux.sec05;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class Deffer {
    public static void main(String[] args) {
        Mono<Void> mono = getMono();

        Mono<Void> d = Mono.defer(Deffer::getMono);

        log.info("starting subscribe mono....");
        mono.subscribe(Util.subscriber());
    }


    private static Mono<Void> getMono() {
        log.info("in init mono");
        return Mono.fromRunnable(() -> log.info("then"));
    }
}
