package com.example.webflux.sec02;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxRange {
    public static void main(String[] args) {
        //publisher
        Flux<Integer> rangeFlux = Flux.range(3, 2);

        rangeFlux
                .log()
                .map(i -> Util.faker().name().fullName())
                .log()
                .subscribe(Util.onNext());
    }
}
