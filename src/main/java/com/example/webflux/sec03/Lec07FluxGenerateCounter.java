package com.example.webflux.sec03;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.util.Locale;

public class Lec07FluxGenerateCounter {
    public static void main(String[] args) {
        Flux.generate(
                () -> 1,
                (counter, sink) -> {
                    String country = Util.faker().country().name();
                    sink.next(country);
                    if(counter >= 10 || country.toLowerCase(Locale.ROOT).equals("canada")) {
                        sink.complete();
                    }
                    return counter + 1;
                })
                .take(4)
                .subscribe(Util.subscriber());
    }
}
