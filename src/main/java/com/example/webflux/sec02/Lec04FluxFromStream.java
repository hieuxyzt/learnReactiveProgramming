package com.example.webflux.sec02;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class Lec04FluxFromStream {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        Stream<Integer> stream = list.stream();

//        stream.forEach(System.out::println); //closed
//        stream.forEach(System.out::println);

        Flux<Integer> fluxStream = Flux.fromStream(list::stream); //Create stream
        fluxStream.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );

        fluxStream.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }
}
