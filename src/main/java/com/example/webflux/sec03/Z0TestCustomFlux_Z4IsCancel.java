package com.example.webflux.sec03;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

@Slf4j
public class Z0TestCustomFlux_Z4IsCancel {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
                    for (int i = 0; i < 100; i++) {
                        fluxSink.next(i);
                        if (i == 40) {
                            fluxSink.complete();
                        }
                        if (i == 50) {
                            fluxSink.error(new Exception("wtf"));
                        }
                        if (fluxSink.isCancelled()) {
                            return;
                        }
                    }
                })
                .log()
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        log.info("Received subscription: {}", s);
                        s.request(Integer.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Object o) {
                        log.info("Received object: {}", o);
                    }

                    @Override
                    public void onError(Throwable t) {
                        log.error("Server emmit error: {}", t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        log.info("Complete signal");
                    }
                });
        Util.sleepSeconds(1);
    }
}
