package com.example.webflux.sec06;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Slf4j
public class Lec01ThreadDemo {
    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(fluxSink -> {
            log.info("Create");
            fluxSink.next(1);
        })
                .doOnNext(i -> printThreadName("next " + i));

        flux.subscribe(v -> printThreadName("sub " + v));
        Runnable runnable = () -> flux.subscribe(v -> printThreadName("sub " + v));

        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) {
            executor.execute(runnable);
        }

        for (int i = 0; i < 2; i++) {
            new Thread(runnable).start();
        }

        Util.sleepSeconds(2);
    }



    private static void printThreadName(String msg){
        log.info("[{}] \t {}", Thread.currentThread().getName(), msg);
    }
}


