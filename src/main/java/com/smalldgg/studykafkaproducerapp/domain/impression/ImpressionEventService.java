package com.smalldgg.studykafkaproducerapp.domain.impression;

import com.smalldgg.studykafkaproducerapp.domain.impression.outbound.jpa.ImpressionEventRepository;
import com.smalldgg.studykafkaproducerapp.domain.impression.aggregate.entity.ImpressionDto;
import com.smalldgg.studykafkaproducerapp.domain.impression.aggregate.ImpressionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class ImpressionEventService {

    private final ImpressionEventRepository impressionEventRepository;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    @Transactional
    public void save(ImpressionDto impressionDto) {
        ImpressionEvent event = ImpressionEvent.of(impressionDto);
        impressionEventRepository.save(event);
    }
}
