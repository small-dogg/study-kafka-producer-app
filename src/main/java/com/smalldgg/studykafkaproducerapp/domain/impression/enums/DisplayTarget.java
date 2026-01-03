package com.smalldgg.studykafkaproducerapp.domain.impression.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString(of = {"name"})
@Getter
@RequiredArgsConstructor
public enum DisplayTarget {
    MAIN_HOME("main"),
    KEYWORD("keyword"),
    CATEGORY("category"),
    DETAIL("detail"),
    BANNER("banner"),
    ;

    private final String name;
}
