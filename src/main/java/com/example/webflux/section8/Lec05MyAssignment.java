package com.example.webflux.section8;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

public class Lec05MyAssignment {

    public static void main(String[] args) {
        AtomicLong carPrice = new AtomicLong(100000);
        Flux.combineLatest(depreciatingMonthly(), demandQuarter(), (month, demand) -> {
                    carPrice.set((long) ((carPrice.get() - (month * 100)) * demand));
                    return carPrice;
                })
                .subscribe(Util.subscriber());
        Util.sleepSeconds(20);
    }

    /**
     * Assuming a month = a second
     *
     * @return publisher
     */
    private static Flux<Long> depreciatingMonthly() {
        return Flux.interval(Duration.ZERO, Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("monthly: " + i));
    }

    private static Flux<Double> demandQuarter() {
        return Flux.interval(Duration.ZERO, Duration.ofSeconds(3))
                .map(i -> Util.randomDemandRate())
                .startWith(1d)
                .doOnNext(d -> System.out.println("quarter: " + d));
    }
}

