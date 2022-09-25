package com.example.webflux.section8.helper;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {
    private List<String> list = new ArrayList<>();

    public Flux<String> generateNames() {
        return Flux.generate(stringSynchronousSink -> {
                    System.out.println("generated fresh");

                    String name = Util.faker().name().firstName();
                    list.add(name);
                    stringSynchronousSink.next(name);
                    Util.sleepSeconds(3);
                })
                .cast(String.class)
                .startWith(getFromCache()); //join zô như 1 bộ cache
    }

    private Flux<String> getFromCache() {
        return Flux.fromIterable(list);
    }
}
