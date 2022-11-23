package com.example.webflux.section9_batching;

import com.example.webflux.courseUtil.Util;
import com.example.webflux.section9_batching.assignment.OrderProcessor;
import com.example.webflux.section9_batching.assignment.OrderProcessorV2;
import com.example.webflux.section9_batching.assignment.OrderService;
import com.example.webflux.section9_batching.assignment.PurchaseOrder;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Lec06Assignment {
    public static void main(String[] args) {
        Map<String, Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>>> map = Map.of(
                "Kids", OrderProcessorV2.kidsProcessing(),
                "Automotive", OrderProcessorV2.automotiveProcessing()
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
