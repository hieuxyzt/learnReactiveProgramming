package com.example.webflux.sec03;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Locale;

@Slf4j
public class Lec04FluxCreateIssueFix {
    public static void main(String[] args) {

        // only one instance of fluxSink
        Flux.create(fluxSink -> {
            String country;
            int counter = 0;
            do {
                country = Util.faker().country().name();
                log.info("Emitting : " + country);
                fluxSink.next(country);
                counter++;
            } while (!country.toLowerCase(Locale.ROOT).equals("canada") && !fluxSink.isCancelled() && counter < 10);
            fluxSink.complete();
        })
        .take(3)
        .subscribe(Util.subscriber());
    }
}
