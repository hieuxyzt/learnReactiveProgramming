package com.example.webflux.sec01;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Lec06SupplierRefactoring {
    public static void main(String[] args) {
        getName();

        getName()
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(Util.onNext());
        //executing asynchronous somewhere

        String name = getName()
                .subscribeOn(Schedulers.boundedElastic())
                .block();
        //Mono blocking subscriber.blockingGet
        System.out.println(name);

        getName();

        Util.sleepSeconds(4);
    }


    //Publisher
    private static Mono<String> getName() {
        System.out.println("Enter getName method");
        return Mono.fromSupplier(() -> {
            System.out.println("Generating name..");
            Util.sleepSeconds(3);
            return Util.faker().name().fullName();
        }).map(String::toUpperCase);
    }
}
