package com.eleflow.swapi.infrastructure.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public interface EntityBaseRepository<T extends EntityBase> extends JpaRepository<T, UUID>, QuerydslPredicateExecutor<T> {

    default Page<T> findAll(Filter filter, Pageable pageable) {
        var predicate = filter.toPredicate();
        return Objects.isNull(predicate)
                ? findAll(pageable)
                : findAll(predicate, pageable);
    }

    default List<T> findAll(Filter filter) {
        var predicate = filter.toPredicate();
        if (Objects.isNull(predicate)) {
            return findAll();
        }

        var itterable = findAll(predicate);
        List<T> result = new ArrayList<>();
        itterable.forEach(result::add);
        return result;
    }
}
