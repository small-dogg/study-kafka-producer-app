package com.smalldgg.studykafkaproducerapp.domain.impression.in;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ImpressionEventParam {
    private Long pid;
    private String userId;
    private Long amount;
    private String itemId;
}
