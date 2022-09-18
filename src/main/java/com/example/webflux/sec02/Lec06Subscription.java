package com.example.webflux.sec02;

import com.example.webflux.courseUtil.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

public class Lec06Subscription {

    public static void main(String[] args) {

        AtomicReference<Subscription> atomicReference = new AtomicReference<>();
        Flux.range(1, 20)
                .log()
                .subscribeWith(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("Received subscription: " + subscription);
                        atomicReference.set(subscription);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext : " + integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("onNext : " + throwable.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Completed");
                    }
                });
        Util.sleepSeconds(1);
        atomicReference.get().request(3);
        Util.sleepSeconds(1);
        atomicReference.get().request(Long.MAX_VALUE);
        Util.sleepSeconds(1);
        atomicReference.get().cancel();
        atomicReference.get().request(3);
    }
}
