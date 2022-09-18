package com.example.webflux.sec04;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec01Handle {
    public static void main(String[] args) {
        Flux.range(1, 20)
                .handle((integer, synchronousSink) -> {
                    if(integer == 7) {
                        synchronousSink.complete();
                        return;
                    }
                    if(integer % 2 == 0){
                        synchronousSink.next(integer);
                    } else {
                        synchronousSink.next("odd integer: " + integer);
                    }
                })
                .subscribe(Util.subscriber());
    }
}
