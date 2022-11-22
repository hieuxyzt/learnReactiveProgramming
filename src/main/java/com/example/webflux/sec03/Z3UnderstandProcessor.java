package com.example.webflux.sec03;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

/*
    take receive subscription in onSubscribe method to range publisher
    subscriber receive subscription in onSubscribe method to take publisher

    subscriber request(unbounded)
    take request(unbounded)

    subscriber emit onNext()
    take emit onNext()

    ....
    3 times, take cancel()
    take call onComplete to subscriber
 */
public class Z3UnderstandProcessor {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .log()
                //cancel() after request 3 times
                .take(3)
                //call onComplete() to subscriber
                .log()
                .subscribe(Util.subscriber());
    }
}
