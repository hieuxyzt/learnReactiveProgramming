package com.example.webflux.sec01;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscriber {
    public static void main(String[] args) {
        //publisher
        //stream pipeline
        Mono<Integer> mono = Mono.just("ball")
                .map(s -> s.length())
                .map(l -> l/1);

        //1
        //mono.subscribe();

        //2
        mono.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }
}
