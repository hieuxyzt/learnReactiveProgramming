package com.example.webflux.sec03;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Z5UnderstandFluxCreateAndFluxGenerate {
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
            synchronousSink.next(Util.faker().name().fullName());
            synchronousSink.complete();
        })
                .log()
                .take(3)
                .log()
                .subscribe(Util.subscriber());
    }
}
