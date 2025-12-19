package com.smalldgg.studykafkaproducerapp.event.impression;

import com.smalldgg.studykafkaproducerapp.domain.impression.model.ImpressionDto;
import com.smalldgg.studykafkaproducerapp.event.impression.entity.ImpressionEvent;
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
