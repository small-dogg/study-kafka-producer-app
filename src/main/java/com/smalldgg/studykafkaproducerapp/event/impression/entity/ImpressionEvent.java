package com.smalldgg.studykafkaproducerapp.event.impression.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smalldgg.studykafkaproducerapp.config.audit.BaseTimeEntity;
import com.smalldgg.studykafkaproducerapp.domain.impression.model.ImpressionDto;
import com.smalldgg.studykafkaproducerapp.event.enums.EventStatus;
import com.smalldgg.studykafkaproducerapp.event.enums.EventType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
public class ImpressionEvent extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private EventStatus eventStatus;
    private EventType eventType;
    private String payload;
    private Integer retryCount;

    public static ImpressionEvent of(ImpressionDto impressionDto) {
        ImpressionEvent impressionEvent = new ImpressionEvent();
        try {
            impressionEvent.id = UUID.randomUUID();
            impressionEvent.eventStatus = EventStatus.PENDING;
            impressionEvent.eventType = EventType.IMPRESSION;
            impressionEvent.payload = new ObjectMapper().writeValueAsString(impressionDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return impressionEvent;
    }
}
