package br.com.bookon.server.specifications;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.bookon.server.payload.request.postgres.FilterRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public abstract class EntitySpecification<T> {

    public Specification<T> search(FilterRequest filter, Class<?> clazz) {
        return (root, query, cb) -> {
            Predicate specs = filter(filter, root, query, cb);
            return cb.and(specs);
        };
    }

    public abstract Predicate filter(FilterRequest filter, Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb);

    public static Predicate or(CriteriaBuilder cb, List<Predicate> predicates) {
        return cb.or(predicates.toArray(new Predicate[predicates.size()]));
    }

    public static Predicate and(CriteriaBuilder cb, List<Predicate> predicates) {
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
