package com.example.webflux.sec01;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Mono;

public class TestQuiz {

    public static void main(String[] args) {
        get().subscribe(Util.onNext(),
                Util.onError(),
                Util.onComplete());

        get3().subscribe(Util.onNext(),
                Util.onError(),
                Util.onComplete());

        Mono.empty().subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }

    private static Mono<Integer> get() {
        return Mono.fromSupplier(() -> 2 * 3);
    }

    private static Mono<Integer> get3() {
        return Mono.fromRunnable(() -> {
            int a = 1 * 2;
        });
    }
}
