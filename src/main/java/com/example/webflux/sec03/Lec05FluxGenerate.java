package com.example.webflux.sec03;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Lec05FluxGenerate {
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
            log.info("emitting");
            synchronousSink.next(Util.faker().country().name()); //1
//            synchronousSink.error(new RuntimeException("oops"));
        })
//        .take(2)
        .subscribe(Util.subscriber());
    }
}
