package com.tnn.components.transaction;

import org.springframework.data.jpa.domain.Specification;

public class TransactionSpecification {
    public static Specification<Transaction> hasType(TransactionType type) {
        return (root, query, criteriaBuilder) ->
                type != null ? criteriaBuilder.equal(root.get("type"), type) : null;
    }

    public static Specification<Transaction> hasFromAccount(Long fromAccountId) {
        return (root, query, criteriaBuilder) ->
                fromAccountId != null ? criteriaBuilder.equal(root.get("from_id"), fromAccountId) : null;
    }

    public static Specification<Transaction> hasToAccount(Long toAccountId) {
        return (root, query, criteriaBuilder) ->
                toAccountId != null ? criteriaBuilder.equal(root.get("to_id"), toAccountId) : null;
    }

    public static Specification<Transaction> createdAfter(Long timestamp) {
        return (root, query, criteriaBuilder) ->
                timestamp != null ? criteriaBuilder.greaterThanOrEqualTo(root.get("timestamp"), timestamp) : null;
    }

    public static Specification<Transaction> createdBefore(Long timestamp) {
        return (root, query, criteriaBuilder) ->
                timestamp != null ? criteriaBuilder.lessThanOrEqualTo(root.get("timestamp"), timestamp) : null;
    }
}