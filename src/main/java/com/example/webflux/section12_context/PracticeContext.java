package com.example.webflux.section12_context;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.util.context.Context;

public class PracticeContext {
    public static void main(String[] args) {
        publisher()
                .contextWrite(Context.of("user", "hieunn"))
                .subscribe(Util.subscriber());
    }

    public static Flux<String> publisher() {
        return Flux
                .deferContextual(contextView -> {
                    if (contextView.hasKey("user")) {
                        return Flux.range(0, 10)
                                .map(integer -> Util.faker().name().fullName());
                    }
                    return Flux.error(new RuntimeException("not except"));
                })
                .cast(String.class);
    }
}
