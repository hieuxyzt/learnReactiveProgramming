package com.example.webflux.section8;

import com.example.webflux.courseUtil.Util;
import com.example.webflux.section8.helper.AmericaAirlinesFlight;
import com.example.webflux.section8.helper.EmirateFlight;
import com.example.webflux.section8.helper.QatarFlights;
import reactor.core.publisher.Flux;

public class Lec03Merge {
    public static void main(String[] args) {
        Flux<String> merge = Flux.merge(
                EmirateFlight.getFlight(),
                QatarFlights.getFlight(),
                AmericaAirlinesFlight.getFlight());

        merge.subscribe(Util.subscriber());
        Util.sleepSeconds(10);
    }
}
