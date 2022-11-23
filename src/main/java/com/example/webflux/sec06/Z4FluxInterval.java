package com.example.webflux.sec06;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class Z4FluxInterval {
    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(20))
                .log()
                .doOnNext(aLong -> log.info("emit: {}", aLong))
                .take(2)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(1);
    }
}
