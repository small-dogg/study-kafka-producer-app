package com.smalldgg.studykafkaproducerapp.domain.impression.in;

import com.smalldgg.studykafkaproducerapp.domain.impression.enums.DisplayTarget;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ImpressionEventParam {
    private Long groupId;
    private Long partnerId;
    private Long productId;
    private DisplayTarget displayTarget;
    private String displayId;
    private Long amount;
}
