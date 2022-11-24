package com.example.webflux.section12_context;

import com.example.webflux.courseUtil.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

@Slf4j
public class Lec01Ctx {
    public static void main(String[] args) {

        getWelcomeMessage()
                .log()
                .contextWrite(context -> {
                    log.info("update");
                    return context.put("user", context.get("user") + "@vnpay.vn");
                })
                .doFirst(() -> log.info("doFirst"))
                .contextWrite(context -> {
                    log.info("create");
                    return context.put("user", "hieunn");
                })
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage() {
//        return Mono.fromSupplier(() -> "Welcome");
        return Mono.deferContextual(contextView -> {
            if(contextView.hasKey("user")){
                return Mono.just("Welcome " + contextView.get("user"));
            } else {
                return Mono.error(new RuntimeException("unauthenticated"));
            }
        });
    }
}
