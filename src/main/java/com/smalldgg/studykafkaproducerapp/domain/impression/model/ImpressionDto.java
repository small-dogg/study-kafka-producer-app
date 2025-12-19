package com.smalldgg.studykafkaproducerapp.domain.impression.model;

import com.fasterxml.uuid.Generators;
import com.smalldgg.studykafkaproducerapp.domain.impression.in.ImpressionEventParam;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImpressionDto {
    private String id;
    private Long pid;
    private Long userId;
    private Long amount;
    private Long itemId;
    private LocalDateTime timestamp;

    public static ImpressionDto of(ImpressionEventParam param) {
        ImpressionDto impressionDto = new ImpressionDto();
        impressionDto.id = Generators.defaultTimeBasedGenerator().generate().toString();
        impressionDto.pid = param.getPid();
        impressionDto.userId = param.getUserId();
        impressionDto.amount = param.getAmount();
        impressionDto.itemId = param.getItemId();
        impressionDto.timestamp = LocalDateTime.now();

        return impressionDto;
    }
}
