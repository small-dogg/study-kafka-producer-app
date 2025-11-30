package com.smalldgg.studykafkaproducerapp.domain.impression;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smalldgg.studykafkaproducerapp.domain.impression.in.ImpressionEventParam;
import com.smalldgg.studykafkaproducerapp.domain.impression.model.ImpressionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LogService {
    private static final String TOPIC = "impression-events";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Transactional
    public void sendClick(ImpressionEventParam param) {
        try {
            ImpressionEvent impressionEvent = ImpressionEvent.of(param);
            String json = objectMapper.writeValueAsString(impressionEvent);
            // key는 campaignId로

            kafkaTemplate.executeInTransaction(operations -> {
               log.info("START TRANSACTION ---");

                this.business(impressionEvent);

                operations.send(TOPIC, String.valueOf(impressionEvent.getId()), json);

                return null;
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void business(ImpressionEvent impressionEvent) {
    }
}
