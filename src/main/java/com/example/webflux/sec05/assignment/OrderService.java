package com.example.webflux.sec05.assignment;

import lombok.Getter;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * Create a publisher send 2 request to 2 subscriber/consumer
 * Send 1 item/0.1 s
 */
@Getter
public class OrderService {
    private final Flux<PurchaseOrder> flux;

    public OrderService() {
        this.flux = getStreamOrder();
    }

    /**
     * Wait 2 subscribers to start
     * @return publisher
     */
    private Flux<PurchaseOrder> getStreamOrder(){
        return Flux.interval(Duration.ofMillis(100))
                .map(i -> new PurchaseOrder())
                .publish()
                .refCount(2);
    }
}
