package com.example.webflux.section9_batching;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Consumer;

public class Z1TestBatchingGroup {
    public static void main(String[] args) {
        Flux.range(0, 10)
                .delayElements(Duration.ofMillis(40))
                .groupBy(i -> i % 2)
                .subscribe(gf -> gf.subscribe(
                        new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) {
                                System.out.println("Key: " + gf.key() + " Item: " + integer);
                            }
                        }
                ));
        Util.sleepSeconds(5);
    }
}
