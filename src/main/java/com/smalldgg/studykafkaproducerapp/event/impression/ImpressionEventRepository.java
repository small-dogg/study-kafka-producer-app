package com.smalldgg.studykafkaproducerapp.event.impression;

import com.smalldgg.studykafkaproducerapp.event.enums.EventStatus;
import com.smalldgg.studykafkaproducerapp.event.impression.entity.ImpressionEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ImpressionEventRepository extends JpaRepository<ImpressionEvent, UUID> {

    Optional<ImpressionEvent> findByIdAndEventStatus(UUID id, EventStatus eventStatus);
}
