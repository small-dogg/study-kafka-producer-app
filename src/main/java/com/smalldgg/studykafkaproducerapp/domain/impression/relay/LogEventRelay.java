package com.smalldgg.studykafkaproducerapp.domain.impression.relay;

import com.smalldgg.studykafkaproducerapp.domain.impression.outbound.jpa.ImpressionEventRepository;
import com.smalldgg.studykafkaproducerapp.domain.impression.aggregate.ImpressionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LogEventRelay {
    private static final String TOPIC = "impression";
    private final ImpressionEventRepository impressionEventRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    @Scheduled(fixedRate = 1000)
    public void sendImpressionEvent() {
        List<ImpressionEvent> impressionEvents = impressionEventRepository.findImpressionEventsWithSkipLock();

        if (!CollectionUtils.isEmpty(impressionEvents)) {
            for (ImpressionEvent event : impressionEvents) {
                kafkaTemplate.send(TOPIC, String.valueOf(event.getId()), event.getPayload())
                        .whenComplete((res, ex) -> {
                            if (ex == null) {
                                event.markSuccess();
                            } else {
                                event.countUp();
                            }
                        });
            }
        }
    }
}
