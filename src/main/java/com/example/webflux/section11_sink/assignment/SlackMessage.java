package com.example.webflux.section11_sink.assignment;

import lombok.Data;

@Data
public class SlackMessage {
    private static final String messageFormat = "[%s] to [%s], message: %s";

    private String sender;
    private String recipient;
    private String message;

    @Override
    public String toString() {
        return String.format(messageFormat, sender, recipient, message);
    }
}
