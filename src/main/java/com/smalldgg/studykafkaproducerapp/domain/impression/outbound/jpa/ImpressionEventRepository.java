package com.smalldgg.studykafkaproducerapp.domain.impression.outbound.jpa;

import com.smalldgg.studykafkaproducerapp.domain.impression.aggregate.ImpressionEvent;
import com.smalldgg.studykafkaproducerapp.event.enums.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImpressionEventRepository extends JpaRepository<ImpressionEvent, String> {

    Optional<ImpressionEvent> findByIdAndEventStatus(String id, EventStatus eventStatus);

    @Query(value = """
            SELECT 
                impe
            FROM impression_event impe
            WHERE impe.event_status = 'PENDING'
            ORDER BY impe.create_time DESC
            LIMIT 100
            FOR UPDATE skip locked
        """, nativeQuery = true
    )
    List<ImpressionEvent> findImpressionEventsWithSkipLock();
}
