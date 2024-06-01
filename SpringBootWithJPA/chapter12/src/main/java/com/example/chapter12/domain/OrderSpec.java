package com.example.chapter12.domain;

import com.example.chapter12.domain.enums.OrderStatus;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.thymeleaf.util.StringUtils;

public class OrderSpec {

    public static Specification<Order> memberName(final String memberName){

        return new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (StringUtils.isEmpty(memberName)) return null;

                Join<Order, Member> m = root.join("member", JoinType.INNER);
                return criteriaBuilder.equal(m.get("name"), memberName);
            }
        };
    }

    public static Specification<Order> isOrderStatus(){
        return new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("status"), OrderStatus.ORDER);
            }
        };
    }

}
