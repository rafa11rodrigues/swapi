package com.eleflow.swapi.domain.planet;

import com.eleflow.swapi.infrastructure.domain.Filter;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.util.StringUtils;

public class PlanetFilter implements Filter {

    private String name;

    public PlanetFilter(String name) {
        this.name = name;
    }


    @Override
    public Predicate toPredicate() {
        return new BooleanBuilder()
                .and(hasName() ? PlanetSpecification.byName(name) : null)
                .getValue();
    }


    private boolean hasName() {
        return StringUtils.hasText(name);
    }
}
