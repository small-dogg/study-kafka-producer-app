package com.smalldgg.studykafkaproducerapp.domain.impression;

import com.smalldgg.studykafkaproducerapp.domain.impression.in.ImpressionEventParam;
import com.smalldgg.studykafkaproducerapp.domain.impression.aggregate.entity.ImpressionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LogService {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void impression(ImpressionEventParam param) {

        ImpressionDto impressionDto = ImpressionDto.of(param);
        /** Somthing to do */
        applicationEventPublisher.publishEvent(impressionDto);

    }
}
