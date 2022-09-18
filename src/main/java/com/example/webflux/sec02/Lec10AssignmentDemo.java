package com.example.webflux.sec02;

import com.example.webflux.sec02.assignment.StockPriceUtil;
import com.example.webflux.sec02.assignment.StockSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CountDownLatch;

public class Lec10AssignmentDemo {
    public static void main(String[] args) throws InterruptedException {
        Flux<Long> stock = StockPriceUtil.get();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        StockSubscriber subscriber = new StockSubscriber(countDownLatch);

        stock
                .subscribeOn(Schedulers.boundedElastic())
                .subscribeWith(subscriber);

//        while (!subscriber.isCompletedStock()){
//            Util.sleepSeconds(2);
//        }

        countDownLatch.await();

    }
}
