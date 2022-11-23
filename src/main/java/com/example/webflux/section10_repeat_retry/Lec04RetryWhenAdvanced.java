package com.example.webflux.section10_repeat_retry;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
public class Lec04RetryWhenAdvanced {
    public static void main(String[] args) {

        orderService("Test")
                .doOnError(throwable -> log.error(throwable.getMessage()))
                .retryWhen(Retry.from(
                        flux -> flux
                                .doOnNext(retrySignal -> {
                                    log.info("total retries: {}", retrySignal.totalRetries());
                                    log.info("failure", retrySignal.failure());
                                })
                                .handle((retrySignal, synchronousSink) -> {
                                    if(retrySignal.failure().getMessage().equals("500")) {
                                        log.info("skip as success");
                                        synchronousSink.next(10000);
                                    } else {
                                        synchronousSink.error(retrySignal.failure());
                                    }
                                })
                                .delayElements(Duration.ofSeconds(20))
                ))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);

    }
//    order service
    private static Mono<String> orderService(String ccNumber) {
        return Mono.fromSupplier(() -> {
           processPayment(ccNumber);
           return Util.faker().idNumber().valid();
        });
    }
//    payment service
    private static void processPayment(String ccNumber) {
        int random = Util.faker().random().nextInt(1, 10);
        if(random < 4) {
            throw new RuntimeException("500");
        } else if (random < 9) {
            throw new RuntimeException("404");
        }
    }
}
