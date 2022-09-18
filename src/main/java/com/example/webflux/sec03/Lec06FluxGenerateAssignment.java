package com.example.webflux.sec03;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec06FluxGenerateAssignment {
    public static void main(String[] args) {

        // canada
        // max = 10
        // subscriber cancels - exit


        AtomicInteger atomicCount = new AtomicInteger(0);
        Flux.generate(synchronousSink -> {
            String name = Util.faker().country().name();
            synchronousSink.next(name);
            atomicCount.incrementAndGet();
            if(name.toLowerCase(Locale.ROOT).equals("canada")){
                synchronousSink.complete();
            }
        })
//                .take(2)
                .subscribe(Util.subscriber());
    }
}
