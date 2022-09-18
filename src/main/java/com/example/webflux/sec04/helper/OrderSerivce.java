package com.example.webflux.sec04.helper;

import com.example.webflux.courseUtil.Util;
import lombok.Getter;
import lombok.ToString;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OrderSerivce {
    private static Map<Integer, List<PurchaseOrder>> db = new HashMap<>();

    static {
        List<PurchaseOrder> list1 = Arrays.asList(
                new PurchaseOrder(1),
                new PurchaseOrder(1),
                new PurchaseOrder(1)
        );
        List<PurchaseOrder> list2 = Arrays.asList(
                new PurchaseOrder(2),
                new PurchaseOrder(2)
        );
        List<PurchaseOrder> list3 = Arrays.asList(
                new PurchaseOrder(3),
                new PurchaseOrder(3)
        );
        List<PurchaseOrder> list4 = Arrays.asList(
                new PurchaseOrder(4),
                new PurchaseOrder(4)
        );
        List<PurchaseOrder> list5 = Arrays.asList(
                new PurchaseOrder(5),
                new PurchaseOrder(5),
                new PurchaseOrder(5),
                new PurchaseOrder(5)
        );

        db.put(1, list1);
        db.put(2, list2);
        db.put(3, list3);
        db.put(4, list4);
        db.put(5, list5);
    }

    public static Flux<PurchaseOrder> getOrders(int userId) {
        return Flux.create((FluxSink<PurchaseOrder> purchaseOrderFluxSink) -> {
                    db.get(userId).forEach(purchaseOrderFluxSink::next);
                    purchaseOrderFluxSink.complete();
                }).log()
                .delayElements(Duration.ofSeconds(5));
//                .map(PurchaseOrder.class::cast);

    }
}
