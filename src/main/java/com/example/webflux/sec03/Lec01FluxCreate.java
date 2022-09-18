package com.example.webflux.sec03;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.util.Locale;

public class Lec01FluxCreate {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            String country;
            do {
                country = Util.faker().country().name();
                fluxSink.next(country);
            } while (!country.toLowerCase(Locale.ROOT).equals("canada"));
            fluxSink.complete();
        })
        .subscribe(Util.subscriber());

    }
}
