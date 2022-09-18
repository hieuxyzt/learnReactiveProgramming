package com.example.webflux.sec01;

import com.example.webflux.courseUtil.Util;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyOrError {
    public static void main(String[] args) {
        userRepository(3)
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        Util.onComplete()
                );
    }

    private static Mono<String> userRepository(int userId){
        if(userId == 1){
            return Mono.just(Util.faker().name().firstName());
        }
        if (userId == 2) {
            return Mono.empty();
        }

        return Mono.error(new Exception("Not in the allowed range"));
    }
}
