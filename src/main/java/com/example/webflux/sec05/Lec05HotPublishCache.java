package com.example.webflux.sec05;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

@Slf4j
public class Lec05HotPublishCache {
    public static void main(String[] args) {
        // share() = publish().refCount(1)
        // cache() = publish().replay()
        Flux<String> movieStream = Flux.fromStream(Lec05HotPublishCache::getMovie)
                .delayElements(Duration.ofSeconds(1))
                .cache(2);

        Util.sleepSeconds(5);
        movieStream
                .subscribe(Util.subscriber("sam"));

        // wait 4 seconds
        Util.sleepSeconds(7);
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
