package com.smalldgg.studykafkaproducerapp.domain.impression.aggregate;

import com.fasterxml.uuid.Generators;
import com.smalldgg.studykafkaproducerapp.config.audit.BaseTimeEntity;
import com.smalldgg.studykafkaproducerapp.domain.impression.aggregate.entity.ImpressionDto;
import com.smalldgg.studykafkaproducerapp.event.enums.EventStatus;
import com.smalldgg.studykafkaproducerapp.event.enums.EventType;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
@Table(name="impression_event")
public class ImpressionEvent extends BaseTimeEntity {
    private static final String ID_PREFIX = "ad-impression";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    private String payload;
    private Integer retryCount;

    public static ImpressionEvent of(ImpressionDto impressionDto) {
        ImpressionEvent impressionEvent = new ImpressionEvent();
        impressionEvent.id = ID_PREFIX + Generators.defaultTimeBasedGenerator().generate().toString();
        impressionEvent.eventStatus = EventStatus.PENDING;
        impressionEvent.eventType = EventType.IMPRESSED;
        impressionEvent.payload = impressionDto.retrievePayload();

        return impressionEvent;
    }

    public void markSuccess() {
        this.eventStatus = EventStatus.SUCCESS;
    }

    public void countUp() {
        this.retryCount++;
        if (this.retryCount >= 10) {
            eventStatus = EventStatus.FAIL;
        }
    }
}
