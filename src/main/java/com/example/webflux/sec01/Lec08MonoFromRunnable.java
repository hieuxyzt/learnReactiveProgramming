package com.example.webflux.sec01;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Mono;

public class Lec08MonoFromRunnable {
    public static void main(String[] args) {
//        Runnable runnable = () -> System.out.println("");

        Mono.fromRunnable(timConsumingProcess())
                .subscribe(Util.onNext(),
                        Util.onError(),
                        () -> {
                            System.out.println("Processed is done. Sending email...");
                        });


    }
    private static Runnable timConsumingProcess() {
        return () -> {
            Util.sleepSeconds(3);
            System.out.println("Operation complete");
        };
    }
}
