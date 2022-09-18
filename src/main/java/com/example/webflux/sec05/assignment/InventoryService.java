package com.example.webflux.sec05.assignment;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
public class InventoryService {
    private Map<String, Integer> db = new HashMap<>();
    public InventoryService() {
        db.put("Kids", 100);
        db.put("Automotive", 100);
        db.put("Jewelry", 100);
    }

    public Consumer<PurchaseOrder> subscribeOrderStream() {
        return purchaseOrder -> db.computeIfPresent(purchaseOrder.getCategory(), (k,v) -> v - purchaseOrder.getQuantity());
    }

    public Flux<String> inventoryStream(){
        return Flux.interval(Duration.ofSeconds(1))
                .map(l -> db.toString());
    }
}
