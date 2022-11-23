package com.example.webflux.sec06;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class Z4SubscribeOnPublishOn {
    public static void main(String[] args) {
//        Flux.range(0, 2)
//                .log()
//                .doOnNext(integer -> log.info("first flux emit {}", integer))
//                .subscribeOn(Schedulers.boundedElastic())
//                .doOnNext(integer -> log.info("second flux emit {}", integer))
//                .doFirst(() -> log.info("do first"))
//                .publishOn(Schedulers.parallel())
//                .doOnNext(integer -> log.info("third flux emit {}", integer))
//                .doFirst(() -> log.info("do first"))
//                .subscribe(Util.subscriber());

        Flux<Integer> publisher = getPublisher();
        publisher
                .publishOn(Schedulers.newParallel("subscribe thread 1", 1))
                .doOnNext(integer -> log.info("third flux emit {}", integer))
                .subscribe(Util.subscriber());

        publisher
                .publishOn(Schedulers.newParallel("subscribe thread 2", 1))
                .doOnNext(integer -> log.info("third flux emit {}", integer))
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> getPublisher() {
        return Flux.range(0, 2)
                .log()
                .doOnNext(integer -> log.info("first flux emit {}", integer))
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(integer -> log.info("second flux emit {}", integer))
                .doFirst(() -> log.info("do first"));
    }
}
