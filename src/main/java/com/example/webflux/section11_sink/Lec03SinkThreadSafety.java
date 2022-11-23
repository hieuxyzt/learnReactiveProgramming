package com.example.webflux.section11_sink;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


/*
*  Not safety, need retry handler/failure handler
* */
@Slf4j
public class Lec03SinkThreadSafety {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();

        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();
        sink.asFlux().subscribe(list::add);

        /*for (int i = 0; i < 1000; i++) {
            int finalI = i;
            CompletableFuture.runAsync(() -> sink.tryEmitNext(finalI));
        }*/

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            CompletableFuture.runAsync(() -> sink
                    .emitNext(finalI, (signalType, emitResult) -> {
                        if (emitResult.isFailure()) {
                            log.error("Fail, Retry");
                        }
                        return true;
                    }));
        }

        Util.sleepSeconds(1);
        System.out.println(list.size());
    }
}
