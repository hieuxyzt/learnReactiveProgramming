package com.example.webflux.sec04;

import com.example.webflux.courseUtil.Util;
import com.example.webflux.sec04.helper.Person;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Locale;
import java.util.function.Function;

@Slf4j
public class Lec11SwitchOnFirst {
    public static void main(String[] args) {
        getPerson()
                .switchOnFirst((signal, personFlux) -> {
                    log.info("inside switch on first");
                    return signal.isOnNext() && signal.get().getAge() > 10 ? personFlux : applyFilterMap().apply(personFlux);
                })

                .subscribe(Util.subscriber());
    }
    // publisher 1
    public static Flux<Person> getPerson(){
        return Flux.range(1, 10)
                .map(i -> new Person());
    }

    // publisher downstream
    public static Function<Flux<Person>, Flux<Person>> applyFilterMap(){
        return flux -> flux.filter(p -> p.getAge() > 10)
                .doOnNext(p -> p.setName(p.getName().toUpperCase(Locale.ROOT)))
                .doOnDiscard(Person.class, p -> log.info("Not allowing: {}", p));
    }
}
