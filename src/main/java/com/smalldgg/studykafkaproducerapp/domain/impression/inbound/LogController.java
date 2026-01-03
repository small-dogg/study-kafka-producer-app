package com.smalldgg.studykafkaproducerapp.domain.impression.inbound;

import com.smalldgg.studykafkaproducerapp.domain.impression.LogService;
import com.smalldgg.studykafkaproducerapp.domain.impression.in.ImpressionEventParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/log")
@RequiredArgsConstructor
@RestController
public class LogController {

    private final LogService logService;

    @RequestMapping("/impression")
    @PostMapping
    public void impression(@RequestBody ImpressionEventParam param) {
        logService.impression(param);

    }
    @RequestMapping("/click")
    @PostMapping
    public void click(@RequestBody ImpressionEventParam param) {
        logService.impression(param);

    }

}
