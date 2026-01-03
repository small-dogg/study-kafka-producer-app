package com.smalldgg.studykafkaproducerapp.domain.impression.relay;

import com.smalldgg.studykafkaproducerapp.domain.impression.outbound.jpa.ImpressionEventRepository;
import com.smalldgg.studykafkaproducerapp.event.enums.EventStatus;
import com.smalldgg.studykafkaproducerapp.domain.impression.aggregate.ImpressionEvent;
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
        ImpressionEvent event = impressionEventRepository.findByIdAndEventStatus(impressionEvent.getId(), EventStatus.PENDING)
                .orElseThrow(() -> new IllegalArgumentException("이벤트를 찾을 수 없습니다."));

        kafkaTemplate.send(TOPIC, String.valueOf(event.getId()), impressionEvent.getPayload())
                .whenComplete((res, ex) -> {
                    if (ex == null) {
                        event.markSuccess();
                    } else {
                        event.countUp();
                    }
                });
    }
}
