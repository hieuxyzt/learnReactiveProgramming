package com.example.webflux.sec02.assignment;

import com.example.webflux.courseUtil.Util;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Slf4j
public class StockPriceUtil {
    public static AtomicLong amount = new AtomicLong(100L);

    public static final int bound = 7;
    public static Flux<Long> get() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> getAmount());
    }

    private static long getAmount() {
        return amount.getAndAccumulate(Util.faker().random().nextInt(-bound, bound), Long::sum);
//        long result = Util.faker().random().nextInt(-bound, bound);
//        log.info("Send: {}", result);
//        return amount.addAndGet(result);
//        Lỗi khi có nhiều publisher

    }

}
