package com.example.webflux.sec05;

import com.example.webflux.courseUtil.DefaultSubscriber;
import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

@Slf4j
public class Lec04HotPublishAutoConnect {
    public static void main(String[] args) {
        // share() = publish().refCount(1)
        Flux<String> movieStream = Flux.fromStream(Lec04HotPublishAutoConnect::getMovie)
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .autoConnect(1);

        // khac voi refCount o diem publish hoat dong ngay khi k co subcriber nao
        // va tu dong connect khi co subscribers subscribe
        // wait 3 seconds
        Util.sleepSeconds(3);
        long start = System.currentTimeMillis();
        DefaultSubscriber sam = (DefaultSubscriber) Util.subscriber("sam");
        movieStream
                .subscribe(sam);
        while(System.currentTimeMillis() - start < 3000){
            Util.sleepSeconds(1);
        }
        sam.getSubscription().cancel();
        // wait 4 seconds
        Util.sleepSeconds(6);
        System.out.println("Mike is about to join");
        movieStream
                .log()
                .subscribe(Util.subscriber("mike"));
        Util.sleepSeconds(20);
    }

    // movie theater
    private static Stream<String> getMovie(){
        System.out.println("Got the movie streaming req");
        return Stream.of(
                "Science 1",
                "Science 2",
                "Science 3",
                "Science 4",
                "Science 5",
                "Science 6",
                "Science 7",
                "Science 8",
                "Science 9"
        );
    }
}
