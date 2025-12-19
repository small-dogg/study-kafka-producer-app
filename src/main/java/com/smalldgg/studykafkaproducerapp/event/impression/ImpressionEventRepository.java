package com.smalldgg.studykafkaproducerapp.event.impression;

import com.smalldgg.studykafkaproducerapp.event.enums.EventStatus;
import com.smalldgg.studykafkaproducerapp.event.impression.entity.ImpressionEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImpressionEventRepository extends JpaRepository<ImpressionEvent, UUID> {

    Optional<ImpressionEvent> findByIdAndEventStatus(UUID id, EventStatus eventStatus);

    @Query(value = """
            SELECT 
                impe
            FROM ImpressionEvent impe
            WHERE impe.eventStatus = 'PENDING'
            ORDER BY impe.createTime DESC
            LIMIT 100
            FOR UPDATE skip locked
        """, nativeQuery = true
    )
    List<ImpressionEvent> findImpressionEventsWithSkipLock();
}
