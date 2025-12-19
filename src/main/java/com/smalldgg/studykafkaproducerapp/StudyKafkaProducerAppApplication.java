package com.smalldgg.studykafkaproducerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StudyKafkaProducerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyKafkaProducerAppApplication.class, args);
    }

}
