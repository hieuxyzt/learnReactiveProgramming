package com.example.webflux.courseUtil;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;

import java.util.function.Consumer;

@Slf4j
public class Util {
    public static final Faker FAKER = Faker.instance();


    public static Consumer<Object> onNext() {
        return o -> log.info("Received: {}", o);
    }

    public static Consumer<Throwable> onError() {
//        return e -> log.info();("ERROR: " + e.getMessage());
        return new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                log.info(throwable.getMessage());
            }
        };
    }

    public static Runnable onComplete() {
        return () -> log.info("Complete");
    }

    public static Runnable onComplete(String info) {
        return () -> log.info(info);
    }

    public static Faker faker() {
        return FAKER;
    }

    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleepMillis(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static Subscriber<Object> subscriber() {
        return new DefaultSubscriber();
    }

    public static Subscriber<Object> subscriber(String name) {
        return new DefaultSubscriber(name);
    }

    public static Double randomDemandRate() {
        return Util.faker().random().nextInt(80, 120) / 100D;

    }
}
