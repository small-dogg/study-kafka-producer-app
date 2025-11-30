package com.smalldgg.studykafkaproducerapp.domain.impression.model;

import com.fasterxml.uuid.Generators;
import com.smalldgg.studykafkaproducerapp.domain.impression.in.ImpressionEventParam;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImpressionEvent {
    private String id;
    private Long pid;
    private Long userId;
    private Long amount;
    private Long itemId;
    private LocalDateTime timestamp;

    public static ImpressionEvent of(ImpressionEventParam param) {
        ImpressionEvent impressionEvent = new ImpressionEvent();
        impressionEvent.id = Generators.defaultTimeBasedGenerator().generate().toString();
        impressionEvent.pid = param.getPid();
        impressionEvent.userId = param.getUserId();
        impressionEvent.amount = param.getAmount();
        impressionEvent.itemId = param.getItemId();
        impressionEvent.timestamp = LocalDateTime.now();

        return impressionEvent;
    }
}
