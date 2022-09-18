package com.example.webflux.courseUtil;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@NoArgsConstructor
@Slf4j
@Getter
public class DefaultSubscriber implements Subscriber<Object> {
    private String name = "";

    Subscription subscription;

    public DefaultSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Object o) {
        log.info("{} Received: {}", name, o);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("{} Error: {}", name, throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("{} Completed", name);
    }

}
