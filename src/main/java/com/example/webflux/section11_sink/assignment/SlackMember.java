package com.example.webflux.section11_sink.assignment;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Data
@Slf4j
public class SlackMember {
    private String name;

    private Consumer<String> messageConsumer;

    public SlackMember(String name) {
        this.name = name;
    }

    public void receives(String message) {
        log.info(message);
    }

    public void says(String message) {
        messageConsumer.accept(message);
    }
}
