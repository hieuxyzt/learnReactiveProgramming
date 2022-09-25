package com.example.webflux.section8;

import com.example.webflux.courseUtil.Util;
import com.example.webflux.section8.helper.NameGenerator;

public class Lec01StartWith {
    public static void main(String[] args) {
        NameGenerator generator = new NameGenerator();
        generator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("sam"));
//        Do lúc đầu Fluxstart empty nên nó k lấy từ FluxStart nữa

//        generator.generateNames()
//                .take(2)
//                .subscribe(Util.subscriber("mike"));
//
//        generator.generateNames()
//                .take(3)
//                .subscribe(Util.subscriber("jake"));
//        generator.generateNames()
//                .filter(n -> n.startsWith("A"))
//                .take(1)
//                .subscribe(Util.subscriber("hieu"));
    }
}
