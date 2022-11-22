package com.example.webflux.sec03;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class Z1RefactorCustomFluxByTheFuckingWay {
    public static void main(String[] args) {
        NameProducer nameProducer = new NameProducer();
        NameSubscriber nameSubscriber = new NameSubscriber();

        Flux.create(nameProducer)
                .log()
                .subscribe(nameSubscriber);

        nameProducer.produce();
    }

    static class NameProducer implements Consumer<FluxSink<String>> {
        FluxSink<String> fluxSink;

        @Override
        public void accept(FluxSink<String> fluxSink) {
            this.fluxSink = fluxSink;
        }

        public void produce() {
            fluxSink.next(Util.faker().name().fullName());
        }
    }

    @Slf4j
    static class NameSubscriber implements Subscriber<Object> {

        @Override
        public void onSubscribe(Subscription s) {
            s.request(Integer.MAX_VALUE);
        }

        @Override
        public void onNext(Object o) {
            log.info("Received: {}", o);
        }

        @Override
        public void onError(Throwable t) {
            log.error("Error: {}", t.getMessage());
        }

        @Override
        public void onComplete() {
            log.info("on complete");
        }
    }
}
