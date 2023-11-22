package br.com.bookon.server.specifications;

import java.util.ArrayList;
import java.util.List;

import br.com.bookon.server.models.postgres.User;
import br.com.bookon.server.payload.request.postgres.FilterRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class UserSpecification extends EntitySpecification<User> {

    @Override
    public Predicate filter(FilterRequest filter, Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> or = new ArrayList<>();
        or.add(cb.like(cb.lower(root.get("username")), "%" + filter.getQ().toString().toLowerCase() + "%"));
        or.add(cb.like(cb.lower(root.get("email")), "%" + filter.getQ().toString().toLowerCase() + "%"));

        Predicate[] orArray = or.toArray(new Predicate[0]);
        Predicate orPredicate = cb.or(orArray);

        return cb.and(orPredicate);

    }

}
