package com.smalldgg.studykafkaproducerapp.domain.impression.in;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ImpressionEventParam {
    private Long pid;
    private Long userId;
    private Long amount;
    private Long itemId;
}
