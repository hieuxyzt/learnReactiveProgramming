package com.example.webflux.sec03;

import com.example.webflux.courseUtil.Util;
import com.example.webflux.sec03.helper.NameProducer;
import reactor.core.publisher.Flux;

public class Lec02FluxCreateRefactoring {
    public static void main(String[] args) {
        NameProducer nameProducer = new NameProducer();

        Flux.create(nameProducer)
                .subscribe(Util.subscriber());

        //Short for () -> nameProducer.producer();
        //Emitting data
        Runnable runnable = nameProducer::produce;

        //Thread safe
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }

        Util.sleepSeconds(2);
    }
}
