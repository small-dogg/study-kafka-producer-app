package com.smalldgg.studykafkaproducerapp.event.impression.command;

import com.smalldgg.studykafkaproducerapp.event.impression.entity.ImpressionEvent;

import java.time.LocalDateTime;

public class ImpressionEventCommand {
    private UUID id;
    private Long pid;
    private Long userId;
    private Long amount;
    private Long itemId;
    private LocalDateTime timestamp;

    public static ImpressionEventCommand of(ImpressionEvent param) {
        ImpressionEventCommand command = new ImpressionEventCommand();
        command.id = param.getId();
        command.amount = param.getAmount();
        command.pid = param.getPid();
        command.userId = param.getUserId();
        command.itemId = param.getItemId();
        command.timestamp = param.getTimestamp();
        return command;
    }
}
