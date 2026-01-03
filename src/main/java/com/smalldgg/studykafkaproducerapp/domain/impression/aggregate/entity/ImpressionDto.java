package com.smalldgg.studykafkaproducerapp.domain.impression.aggregate.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.uuid.Generators;
import com.smalldgg.studykafkaproducerapp.domain.impression.enums.DisplayTarget;
import com.smalldgg.studykafkaproducerapp.domain.impression.in.ImpressionEventParam;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImpressionDto {
    @Getter
    private Long groupId;
    @Getter
    private Long partnerId;
    @Getter
    private Long productId;
    @Getter
    private DisplayTarget displayTarget;
    @Getter
    private String displayId;
    @Getter
    private Long amount;
    @Getter
    private LocalDateTime timestamp;
    private String payload;

    public static ImpressionDto of(ImpressionEventParam param) {
        ImpressionDto impressionDto = new ImpressionDto();
        impressionDto.groupId = param.getGroupId();
        impressionDto.partnerId = param.getPartnerId();
        impressionDto.productId = param.getProductId();
        impressionDto.displayTarget = param.getDisplayTarget();
        impressionDto.displayId = param.getDisplayId();
        impressionDto.amount = param.getAmount();
        impressionDto.timestamp = LocalDateTime.now();

        impressionDto.savePayload();

        return impressionDto;
    }

    public String retrievePayload() {
        return this.payload;
    }

    private void savePayload() {
        try {
            this.payload = new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
