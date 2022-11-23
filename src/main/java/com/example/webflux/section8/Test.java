package com.example.webflux.section8;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class Test {
    public static void main(String[] args) {
        Flux<String> namePublisher = getNamePublisher();
        Flux<String> cache = getNameCache();
        Flux<String> cacheV2 = getNameCacheV2();

        namePublisher
                .startWith(cacheV2)
                .subscribe(Util.subscriber());

        System.out.println("================================================");

        namePublisher
                .concatWith(cacheV2)
                .subscribe(Util.subscriber());

        System.out.println("================================================");

        Flux.merge(
                namePublisher,
                cache,
                cacheV2
        ).subscribe(Util.subscriber());

        System.out.println("================================================");

        Flux.zip(
                namePublisher,
                cacheV2
        ).subscribe(Util.subscriber());

        System.out.println("================================================");

        Mono<Void> mono = Mono.empty().then(Mono.fromRunnable(() -> {
            log.info("oh holly shit");
        }));
        mono.subscribe(Util.subscriber());
    }

    private static Flux<String> getNameCache() {
        return Flux.create(fluxSink -> {
           fluxSink.next("hieu");
           fluxSink.next("hieunn");
           fluxSink.complete();
        });
    }

    private static Flux<String> getNameCacheV2() {
        return Flux.generate(
                () -> 1,
                (count, stringSynchronousSink) -> {
                    if(count == 3){
                        stringSynchronousSink.complete();
                    } else {
                        stringSynchronousSink.next("hieu");
                    }
                    return ++count;
                }
        );
    }

    private static Flux<String> getNamePublisher() {
        return Flux.generate(
                () -> 1,
                (count, stringSynchronousSink) -> {
                    if(count == 5){
                        stringSynchronousSink.complete();
                    } else {
                        stringSynchronousSink.next(Util.faker().name().fullName());
                    }
                    return ++count;
                }
        );
    }
}
