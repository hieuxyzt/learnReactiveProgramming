package com.example.webflux.sec05;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec02HotShare {
    public static void main(String[] args) {
        Flux<String> movieStream = Flux.fromStream(Lec02HotShare::getMovie)
                .delayElements(Duration.ofSeconds(1))
                .share();

        movieStream
                .subscribe(Util.subscriber("sam"));
        Util.sleepSeconds(3);

        movieStream
                .subscribe(Util.subscriber("mike"));
        Util.sleepSeconds(20);
    }

    // TV movie
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
