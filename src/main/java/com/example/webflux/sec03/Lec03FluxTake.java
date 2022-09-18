package com.example.webflux.sec03;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec03FluxTake {
    public static void main(String[] args) {
        //map
        //filter
        Flux.range(1, 10)
                .log()
                .take(3) //cancel upstream subscription, send to downstream
                .log()
                .subscribe(Util.subscriber());
    }
}
