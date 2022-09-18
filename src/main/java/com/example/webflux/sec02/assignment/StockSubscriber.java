package com.example.webflux.sec02.assignment;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.CountDownLatch;

@Slf4j
@NoArgsConstructor
public class StockSubscriber implements Subscriber<Long> {
    private Subscription subscription;
    private Boolean isCompleteStock = false;

    private CountDownLatch countDownLatch;

    public StockSubscriber(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Long o) {
        log.info("Received: {}", o);
        if(o > 110 || o < 90) {
            subscription.cancel();
            isCompleteStock = true;
            log.info("Canceled subscription");
            countDownLatch.countDown();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.info(throwable.getMessage());
        countDownLatch.countDown();
    }

    @Override
    public void onComplete() {
        log.info("Complete");
        countDownLatch.countDown();
    }

    public boolean isCompletedStock(){
        return isCompleteStock;
    }

}
