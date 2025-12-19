package com.smalldgg.studykafkaproducerapp.config.executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class EventTaskExecutor {

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("EventTaskExecutor-");
        executor.setCorePoolSize(2);//기본 스레드수 // 점진적 확보
        executor.setQueueCapacity(2);
        executor.setMaxPoolSize(10);// 최대 스레드 수
//        executor.setPrestartAllCoreThreads(true); // 스레드 풀이 초기화되는 시점에 Core Pool Size 만큼 스레드를 확보한다.
        executor.setWaitForTasksToCompleteOnShutdown(true); // Graceful Shutdown 보장.
        executor.setAwaitTerminationSeconds(10);
        return executor;
    }
}
