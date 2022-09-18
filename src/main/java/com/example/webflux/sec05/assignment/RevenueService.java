package com.example.webflux.sec05.assignment;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
public class RevenueService {
    private Map<String, Double> db = new HashMap<>();
    public RevenueService() {
        db.put("Kids", 100.0);
        db.put("Automotive", 100.0);
        db.put("Jewelry", 100.0);
    }

    /**
     * Consumer consumes Order request
     * Handle Revenue
     * @return consumer;
     */
    public Consumer<PurchaseOrder> subscribeOrderStream() {
        NumberFormat numberFormat = new DecimalFormat(".##");
        return purchaseOrder -> db.computeIfPresent(purchaseOrder.getCategory(), (k, v) -> Double.valueOf(numberFormat.format(v + purchaseOrder.getPrice())));
    }

    /**
     * Publisher send revenue info to customer
     * @return publisher
     */
    public Flux<String> revenueStream() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(l -> db.toString())
                .subscribeOn(Schedulers.boundedElastic());
    }
}
