package com.example.webflux.sec05;

import com.example.webflux.courseUtil.DefaultSubscriber;
import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec03HotPublish {
    public static void main(String[] args) {
        // share() = publish().refCount(1)
        Flux<String> movieStream = Flux.fromStream(Lec03HotPublish::getMovie)
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .refCount(1);
        // wait 2 people "join" to start the movie

        Util.sleepSeconds(3);
        long start = System.currentTimeMillis();
        DefaultSubscriber sam = (DefaultSubscriber) Util.subscriber("sam");
        movieStream
                .subscribe(sam);
        while(System.currentTimeMillis() - start < 3000){
            Util.sleepSeconds(1);
        }
        sam.getSubscription().cancel();
//        Util.sleepSeconds(3);
//        movieStream
//                .subscribe(Util.subscriber("sam"));
        Util.sleepSeconds(6);

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
