package com.smalldgg.studykafkaproducerapp.domain.impression.model;

import com.smalldgg.studykafkaproducerapp.domain.impression.in.ImpressionEventParam;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImpressionEvent {
    private Long pid;
    private String userId;
    private Long amount;
    private String itemId;
    private LocalDateTime timestamp;

    public static ImpressionEvent of(ImpressionEventParam param) {
        ImpressionEvent impressionEvent = new ImpressionEvent();
        impressionEvent.pid = param.getPid();
        impressionEvent.userId = param.getUserId();
        impressionEvent.amount = param.getAmount();
        impressionEvent.itemId = param.getItemId();
        impressionEvent.timestamp = LocalDateTime.now();

        return impressionEvent;
    }
}
