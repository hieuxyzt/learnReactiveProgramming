package com.example.webflux.sec06;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Lec06Parallel {
    public static void main(String[] args) {
        // list is not thread safe
        // not locking
        List<Integer> list = new ArrayList<>();
        Flux.range(1, 10000)
                .publishOn(Schedulers.boundedElastic())
                // 8 thread tương ứng với 8 cores xử lý các item
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe(list::add);

        Flux.range(1, 10)
                .publishOn(Schedulers.boundedElastic())
                // 8 thread tương ứng với 8 cores xử lý các item
                .parallel(10)
                .runOn(Schedulers.parallel())
                .doOnNext(v -> printThreadName("next " + v))
                .sequential()
                .doOnNext(v -> printThreadName("next " + v))
                .subscribe(v -> printThreadName("sub " + v));

        Util.sleepSeconds(2);
        log.info(String.valueOf(list.size()));
    }

    private static void printThreadName(String msg) {
        log.info("[{}]\t{}", Thread.currentThread().getName(), msg);
    }
}
