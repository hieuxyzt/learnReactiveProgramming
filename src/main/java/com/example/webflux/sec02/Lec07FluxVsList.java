package com.example.webflux.sec02;

import com.example.webflux.courseUtil.Util;
import com.example.webflux.sec02.helper.NameGenerator;

import java.util.List;

public class Lec07FluxVsList {
    public static void main(String[] args) {
//        List<String> names = NameGenerator.getNamesOld(5);
//        System.out.println(names);

        NameGenerator.getNames(5)
                .subscribe(Util.onNext());
        // Not waiting 5 seconds
        // if one item is ready, take them
        // Use Flux instead of list is helpful
    }
}
