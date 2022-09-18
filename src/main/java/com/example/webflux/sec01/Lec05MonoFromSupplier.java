package com.example.webflux.sec01;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;

public class Lec05MonoFromSupplier {
    public static void main(String[] args) {
        //Use just only when u have data already
//        Mono<String> mono = Mono.just(getName());

        Mono<String> mono = Mono.fromSupplier(() -> getName());
        mono.subscribe(
//                Util.onNext()
        );

        Callable<String> stringCallable = () -> getName();
        Mono.fromCallable(stringCallable)
                .subscribe(
//                        Util.onNext()
                );
    }

    //Thing should be lazyy. Why??
    //Do thing only if it request
    private static String getName() {
        System.out.println("Generating name..");
        return Util.faker().name().fullName();
    }
}
