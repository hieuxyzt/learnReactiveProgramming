package com.example.webflux.sec02;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class Lec03FluxFromArrayOrList {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("a", "b", "c");
        Flux.fromIterable(strings)
                .subscribe(Util.onNext());


        Integer[] arr = {2, 3, 7 ,4};
        Flux.fromArray(arr)
                .subscribe(Util.onNext());
    }
}
