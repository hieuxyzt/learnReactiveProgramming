package com.example.webflux.section8;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec02Concat {
    public static void main(String[] args) {
        Flux<String> flux1 = Flux.just("a", "b");
        Flux<String> flux2 = Flux.just("c", "d", "e");
        Flux<String> flux3 = Flux.error(new RuntimeException("opp"));

//        Flux<String> flux = flux1.concatWith(flux2);
//        Flux<String> flux = flux1.startWith(flux2);
//        Flux<String> flux = Flux.concat(flux1, flux2);
        Flux<String> flux = Flux.concat(flux1, flux3, flux2);

        flux.subscribe(Util.subscriber());
    }
}
