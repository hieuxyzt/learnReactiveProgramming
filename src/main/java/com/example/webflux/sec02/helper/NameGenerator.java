package com.example.webflux.sec02.helper;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {

    public static Flux<String> getNames(int count){
        return Flux.range(0, count)
                .map(i -> getName());
    }

    public static List<String> getNamesOld(int count) {
        List<String> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            list.add(getName());
        }
        return list;
    }


    public static String getName() {
        Util.sleepSeconds(1);
        return Util.faker().name().fullName();
    }
}
