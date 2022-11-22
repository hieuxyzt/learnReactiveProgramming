package com.example.webflux.section9;

import com.example.webflux.courseUtil.Util;
import com.example.webflux.section9.assignment.OrderProcessor;
import com.example.webflux.section9.assignment.OrderService;
import com.example.webflux.section9.assignment.PurchaseOrder;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Lec06Assignment {
    public static void main(String[] args) {
        Map<String, Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>>> map = Map.of(
                "Kids", OrderProcessor.kidsProcessing(),
                "Automotive", OrderProcessor.automotiveProcessing()
        );

        Set<String> set = map.keySet();

        OrderService.orderStream()
                .filter(p -> set.contains(p.getCategory()))
                .groupBy(PurchaseOrder::getCategory)
                .flatMap(groupedFlux -> map.get(groupedFlux.key()).apply(groupedFlux))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }
}
