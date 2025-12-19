package com.smalldgg.studykafkaproducerapp.event.impression;

import com.smalldgg.studykafkaproducerapp.event.impression.entity.ImpressionEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImpressionEventRepository extends JpaRepository<ImpressionEvent, Long> {

}
