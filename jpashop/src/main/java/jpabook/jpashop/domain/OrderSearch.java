package jpabook.jpashop.domain;

import jpabook.jpashop.domain.order.OrderStatus;
import lombok.Data;

@Data
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;
}
