package com.smalldgg.studykafkaproducerapp.event.impression;

import com.smalldgg.studykafkaproducerapp.event.impression.entity.ImpressionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class ImpressionEventListener {

    private static final String TOPIC = "impression";
    private final ImpressionEventRepository impressionEventRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleImpressionEvent(ImpressionEvent impressionEvent) {
        kafkaTemplate.send(TOPIC, String.valueOf(impressionEvent.getId()), impressionEvent.getPayload());
    }
}
