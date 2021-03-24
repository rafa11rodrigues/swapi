package com.eleflow.swapi.infrastructure.domain;

import com.querydsl.core.types.Predicate;

public interface Filter {

    Predicate toPredicate();
}
