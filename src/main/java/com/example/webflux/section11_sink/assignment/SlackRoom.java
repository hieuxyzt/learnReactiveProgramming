package com.example.webflux.section11_sink.assignment;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Data
@Slf4j
public class SlackRoom {
    private String name;
    private Sinks.Many<SlackMessage> sink;
    private Flux<SlackMessage> flux;


    public SlackRoom(String name) {
        this.name = name;
        this.sink = Sinks.many().replay().all();
        this.flux = sink.asFlux();
    }

    public void joinRoom(SlackMember slackMember) {
        log.info("{} join room {}", slackMember.getName(), name);
        subscribe(slackMember);
        slackMember.setMessageConsumer(msg -> postMessage(msg, slackMember));
    }

    private void subscribe(SlackMember slackMember) {
        flux
                .filter(sm -> !sm.getSender().equals(slackMember.getName()))
                .doOnNext(slackMessage -> slackMessage.setRecipient(slackMember.getName()))
                .map(SlackMessage::toString)
                .subscribe(slackMember::receives);
    }

    private void postMessage(String msg, SlackMember slackMember) {
        SlackMessage slackMessage = new SlackMessage();
        slackMessage.setSender(slackMember.getName());
        slackMessage.setMessage(msg);
        sink.tryEmitNext(slackMessage);
    }
}
