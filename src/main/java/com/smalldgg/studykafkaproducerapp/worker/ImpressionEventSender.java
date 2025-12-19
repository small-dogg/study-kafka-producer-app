package com.smalldgg.studykafkaproducerapp.worker;

import com.smalldgg.studykafkaproducerapp.event.impression.ImpressionEventRepository;
import com.smalldgg.studykafkaproducerapp.event.impression.entity.ImpressionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ImpressionEventSender {

    private static final String TOPIC = "impression";
    private final ImpressionEventRepository impressionEventRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactionalπ
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
