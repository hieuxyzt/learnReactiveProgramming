package com.example.webflux.sec04;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec04LimitRate {
    public static void main(String[] args) {
        Flux.range(1, 100)
                .log()
                .limitRate(10, 9)
                .subscribe(Util.subscriber());
    }
}
