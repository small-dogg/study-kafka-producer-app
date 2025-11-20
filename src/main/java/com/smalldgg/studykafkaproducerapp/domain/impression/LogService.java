package com.smalldgg.studykafkaproducerapp.domain.impression;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smalldgg.studykafkaproducerapp.domain.impression.in.ImpressionEventParam;
import com.smalldgg.studykafkaproducerapp.domain.impression.model.ImpressionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LogService {
    private static final String TOPIC = "impression-events";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendClick(ImpressionEventParam param) {
        try {
            ImpressionEvent impressionEvent = ImpressionEvent.of(param);
            String json = objectMapper.writeValueAsString(impressionEvent);
            // key는 campaignId로
            kafkaTemplate.send(TOPIC, String.valueOf(param.getUserId()), json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
